package shopping.css.samples.v1.cssproducts;

public class Product {
    private final String productId;
    private final String title;
    private final String description;
    private final String productType;
    private final String imageLink;
    private final String productLink;
    private final Double price;
    private final String gtin;
    private final String brand;
    private final String headLineOffer;

    public Product(String productId, String title, String description, String productType, String imageLink, String productLink, Double price, String gtin, String brand, String headLineOffer) {
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.productType = productType;
        this.imageLink = imageLink;
        this.productLink = productLink;
        this.price = price;
        this.gtin = gtin;
        this.brand = brand;
        this.headLineOffer = headLineOffer;
    }

    public String getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getProductType() {
        return productType;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getProductLink() {
        return productLink;
    }

    public Double getPrice() {
        return price;
    }

    public String getGtin() {
        return gtin;
    }

    public String getBrand() {
        return brand;
    }

    public String getHeadLineOffer() {
        return headLineOffer;
    }
}
