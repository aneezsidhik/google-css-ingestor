// Copyright 2023 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package shopping.css.samples.v1.cssproducts;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.shopping.css.v1.CssProductInput;
import com.google.shopping.css.v1.CssProductInputsServiceClient;
import com.google.shopping.css.v1.CssProductInputsServiceSettings;
import com.google.shopping.css.v1.InsertCssProductInputRequest;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import shopping.css.samples.utils.Authenticator;
import shopping.css.samples.utils.Config;

/** This class demonstrates how to insert a CSS Product for a given Account */
public class InsertCssProductInput {

  private static String getParent(String domainId) {
    return String.format("accounts/%s", domainId);
  }

  private static String getName(String domainId, String productId) {
    return String.format("accounts/%s/cssProductInputs/%s", domainId, productId);
  }

  // [START insert_css_product_input]
  public static void insertCssProductInput(Config config, long feedId, String rawProvidedId)
      throws Exception {
    GoogleCredentials credential = new Authenticator().authenticate();

    CssProductInputsServiceSettings cssProductInputsServiceSettings =
        CssProductInputsServiceSettings.newBuilder()
            .setCredentialsProvider(FixedCredentialsProvider.create(credential))
            .build();

    String parent = getParent(config.getDomainId().toString());

    try (CssProductInputsServiceClient cssProductInputsServiceClient =
        CssProductInputsServiceClient.create(cssProductInputsServiceSettings)) {

      InsertCssProductInputRequest request =
          InsertCssProductInputRequest.newBuilder()
              .setParent(parent)
              .setCssProductInput(
                  CssProductInput.newBuilder()
                      .setRawProvidedId(rawProvidedId)
                      .setFeedLabel("GB")
                      .setContentLanguage("en")
                      .setAttributes(
                          com.google.shopping.css.v1.Attributes.newBuilder()
                              .setTitle("Instant Pot Superior Slow Cooker 7.1L")
                              .setHeadlineOfferLink("https://amanaonline.co.uk/store-2/instant-pot-superior-slow-cooker-7-1l-2/")
                              .setHeadlineOfferCondition("New")
                              .setDescription("Discover why our Slow Cooker has been ranked number 1 by Good Housekeeping, scoring an impressive 94/100! Trusted experts say, 'The name of this slow cooker isn't misleading, it truly was superior on test'.The Instant Slow Cooker makes cooking low and slow dishes more convenient than ever, from cooking to table to clean up. Sear and sauté ingredients in the same pot before slow cooking, and then keep your recipe warm until you are ready. When it's time to eat, simply take the lightweight inner pot to the table for serving, and pop it in the dishwasher for easy clean up. And if you don't fancy a slow-cooked meal, our versatile slow cooker also doubles up as a steamer so you can effortlessly prepare healthy steamed meals too! 4 functions in 1: Slow cook, Steam, Sauté/Sear, and Keep Warm. Extra-large 7.1L capacity that can cook up to 10 portions. Large surface area makes sauteing ingredients quick and easy. EasyGrab handles to easily take the lightweight inner pot to the table for serving. Adapt for any recipe with full customisable time and temperature controls. Dishwasher-safe lid and non-stick pot.SKU: 140-6128-01-UK")
                              .setNumberOfOffers(2)
                              .setCppLink("https://amanaonline.co.uk/store-2/instant-pot-superior-slow-cooker-7-1l-2/")
                                  .setImageLink("https://amanaonline.co.uk/wp-content/uploads/2024/11/Instant-Pot-Superior-Slow-Cooker-7.1L.png")
                              .setBrand("Brand")
                              .setGoogleProductCategory("Media > Books")
                              .setGtin("3614030018941")
                              .setHeadlineOfferPrice(
                                  com.google.shopping.type.Price.newBuilder().setAmountMicros((long) (79.99*1_000_000)).setCurrencyCode("GBP").build())
                              .build())
                      .build())
              .setFeedId(feedId)
              .build();

      System.out.println("Sending InsertCssProductInput request");
      CssProductInput response = cssProductInputsServiceClient.insertCssProductInput(request);
      System.out.println("Inserted CssProduct Name below");
      System.out.println(response.getName());
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void insertCssProductInput(Config config, long feedId, Product product)
          throws Exception {
    GoogleCredentials credential = new Authenticator().authenticate();

    CssProductInputsServiceSettings cssProductInputsServiceSettings =
            CssProductInputsServiceSettings.newBuilder()
                    .setCredentialsProvider(FixedCredentialsProvider.create(credential))
                    .build();

    String parent = getParent(config.getDomainId().toString());

    try (CssProductInputsServiceClient cssProductInputsServiceClient =
                 CssProductInputsServiceClient.create(cssProductInputsServiceSettings)) {

      InsertCssProductInputRequest request =
              InsertCssProductInputRequest.newBuilder()
                      .setParent(parent)
                      .setCssProductInput(
                              CssProductInput.newBuilder()
                                      .setRawProvidedId(product.getProductId())
                                      .setFeedLabel("GB")
                                      .setContentLanguage("en")
                                      .setAttributes(
                                              com.google.shopping.css.v1.Attributes.newBuilder()
                                                      .setTitle(product.getTitle())
                                                      .setHeadlineOfferLink(product.getProductLink())
                                                      .setHeadlineOfferCondition("New")
                                                      .setDescription(product.getDescription())
                                                      .setNumberOfOffers(2)
                                                      .setCppLink(product.getProductLink())
                                                      .setImageLink(product.getImageLink())
                                                      .setBrand(product.getBrand())
                                                      .setGoogleProductCategory(product.getProductType())
                                                      .setGtin("3614030018941")
                                                      .setHeadlineOfferPrice(
                                                              com.google.shopping.type.Price.newBuilder().setAmountMicros((long) (product.getPrice()*1_000_000)).setCurrencyCode("GBP").build())
                                                      .build())
                                      .build())
                      .setFeedId(feedId)
                      .build();

      System.out.println("Sending InsertCssProductInput request");
      CssProductInput response = cssProductInputsServiceClient.insertCssProductInput(request);
      System.out.println("Inserted CssProduct Name below");
      System.out.println(response.getName());
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // [END insert_css_product]

  public static void main(String[] args) throws Exception {
    final Config config = Config.load();
    // The ID uniquely identifying each feed
    final long feedId = 0;

    // Create a thread pool to insert multiple CSS Products in parallel
    ExecutorService threadPool = Executors.newCachedThreadPool();
//    List<String> lines = Files.readAllLines(Paths.get("https://amanaonline.co.uk/wp-content/uploads/rex-feed/feed-115099.csv"));
    List<String> lines = Files.readAllLines(Paths.get("src/main/resources/products/feed.csv"));
    lines.remove(0);
    AtomicInteger i = new AtomicInteger();
    System.out.println(lines.size());
    List<Product> products = lines.stream().map(line -> {
//      System.out.println(line);
      String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
      return new Product("rawProvidedId" + i.getAndIncrement(), tokens[1], tokens[2], tokens[3], tokens[4], tokens[8], !"".equals(tokens[5]) ? Double.parseDouble(tokens[5]) : 0, "3614030018941", tokens[7]);
    }).collect(Collectors.toList());

    products.parallelStream().forEach(product -> {
      try {
        insertCssProductInput(config, feedId, product);
      } catch (Exception e) {
        System.out.println(e);
      }
    });
/*    for (int i = 0; i < 1; i++) {
      // The raw ID identifying each product
      final String rawProvidedId = "rawProvidedId" + i;
      threadPool.execute(
          () -> {
            try {
              insertCssProductInput(config, feedId, rawProvidedId);
            } catch (Exception e) {
              System.out.println(e);
            }
          });
    }*/
  }
}
