package ra.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private float price;
    private String description;
    private Date created;
    private int catalogId;
    private int productStatus;

    public Product() {
    }

    public Product(String productId, String productName, float price, String description, Date created, int catalogId, int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.created = created;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public void inputData(Scanner scanner, Product[] arrProducts, int indexProduct, Categories[] arrCategories, int indexCategories) {
        this.productId = inputProductId(scanner, arrProducts, indexProduct);
        this.productName = inputProductName(scanner, arrProducts, indexProduct);
        this.price = inputPrice(scanner);
        this.description = inputDescription(scanner);
        this.created = inputCreated(scanner);
        this.catalogId = inputCatalogId(scanner, arrCategories, indexCategories);
        this.productStatus = inputProductStatus(scanner);
    }

    public String inputProductId(Scanner scanner, Product[] arrProducts, int indexProduct) {
        System.out.println("Nhập vào mã sản phẩm:");
        do {
            String productId = scanner.nextLine();
            if (productId.length() == 4) {
                if (productId.startsWith("C") || productId.startsWith("S") || productId.startsWith("A")) {
                    boolean isExist = false;
                    for (int i = 0; i < indexProduct; i++) {
                        if (arrProducts[i].getProductId().equals(productId)) {
                            isExist = true;
                            break;
                        }
                    }
                    if (isExist) {
                        System.err.println("Mã danh mục đã tồn tại, vui lòng nhập lại");
                    } else {
                        return productId;
                    }
                } else {
                    System.err.println("Mã sản phẩm bắt đầu là ký tự C, S, A, vui lòng nhập lại");
                }
            } else {
                System.err.println("Mã sản phẩm gồm 4 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputProductName(Scanner scanner, Product[] arrProducts, int indexProduct) {
        System.out.println("Nhập vào tên sản phẩm:");
        do {
            String productName = scanner.nextLine();
            if (productName.length() >= 10 && productName.length() <= 50) {
                boolean isExist = false;
                for (int i = 0; i < indexProduct; i++) {
                    if (arrProducts[i].getProductName().equals(productName)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại");
                } else {
                    return productName;
                }
            } else {
                System.err.println("Tên sản phẩm có từ 10-50 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public float inputPrice(Scanner scanner) {
        System.out.println("Nhập vào giá sản phẩm:");
        do {
            float price = Float.parseFloat(scanner.nextLine());
            if (price > 0) {
                return price;
            } else {
                System.err.println("Giá sản phẩm phải lớn hơn 0, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputDescription(Scanner scanner) {
        System.out.println("Nhập vào mô tả sản phẩm:");
        return scanner.nextLine();
    }

    public Date inputCreated(Scanner scanner) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Nhập vào ngày nhập sản phẩm:");
        do {
            String created = scanner.nextLine();
            try {
                Date returnCreated = sdf.parse(created);
                return returnCreated;
            } catch (ParseException e) {
                System.err.println("Định dạng ngày nhập không đúng, vui lòng nhập lại");
            } catch (Exception ex) {
                System.err.println("Có lỗi trong quá trình xử lý, vui lòng nhập lại");
            }

        } while (true);
    }

    public int inputCatalogId(Scanner scanner, Categories[] arrCategories, int indexCategories) {
        while (true) {

            System.out.println("Chọn danh mục sản phẩm:");
            for (int i = 0; i < arrCategories.length; i++) {
                if (arrCategories[i] != null) {
                    System.out.printf("%d. %s\n", i + 1, arrCategories[i].getCatalogName());
                }
            }
            System.out.print("Nhập số thứ tự của danh mục: ");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice >= 1 && choice <= arrCategories.length && arrCategories[choice - 1] != null) {
                return arrCategories[choice - 1].getCatalogId();
            } else {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    public int inputProductStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái sản phẩm 0: đang bán, 1. hết hàng, 2. không bán:");
        do {
            int status = Integer.parseInt(scanner.nextLine());
            if (status == 0 || status == 1 || status == 2) {
                return status;
            } else {
                System.err.println("Trạng thái chỉ nhận giá trị 0,1,2, vui lòng nhập lại");
            }
        } while (true);
    }

    public void displayData(Categories[] arrCategories, int indexCategories) {
        System.out.printf("Mã sản phẩm: %s - Tên sản phẩm: %s - Giá: %.2f\n", this.productId, this.productName, this.price);
        System.out.printf("Mô tả: %s - Ngày nhập: %s\n", this.description, this.created.toString());
        String catalogName = "";
        for (int i = 0; i < indexCategories; i++) {
            if (arrCategories[i].getCatalogId() == this.catalogId) {
                catalogName = arrCategories[i].getCatalogName();
                break;
            }
        }
        System.out.printf("Danh mục: %s - Trạng thái: %s\n", catalogName,
                this.productStatus == 0 ? "Đang bán" : (this.productStatus == 1 ? "Hết hàng" : "Không bán"));
    }

}
