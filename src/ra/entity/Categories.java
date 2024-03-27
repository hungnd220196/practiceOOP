package ra.entity;

import java.util.Scanner;

public class Categories {
    private int catalogId;
    private String catalogName;
    private String description;
    private boolean catalogStatus;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, String description, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public void inputData(Scanner scanner, Categories[] arrCategories, int indexCategories) {
        this.catalogId = inputCatalogId(arrCategories, indexCategories);
        this.catalogName = inputCatalogName(scanner, arrCategories, indexCategories);
        this.description = inputDescriptions(scanner);
        this.catalogStatus = inputCatalogStatus(scanner);
    }

    public int inputCatalogId(Categories[] arrCategories, int indexCategories) {
        if (indexCategories == 0) {
            return 1;
        } else {
            //B1: Tìm max của catalogId
            int max = arrCategories[0].getCatalogId();
            for (int i = 1; i < indexCategories; i++) {
                if (max < arrCategories[i].getCatalogId()) {
                    max = arrCategories[i].getCatalogId();
                }
            }
            //B2: return max+1
            return max + 1;
        }
    }

    public String inputCatalogName(Scanner scanner, Categories[] arrCategories, int indexCategories) {
        System.out.println("Nhập vào tên danh mục:");
        do {
            String catalogName = scanner.nextLine();
            //1. Độ dài tối đa 50 ký tự
            if (catalogName.length() <= 50) {
                //2. Kiểm tra trùng lặp
                boolean isExist = false;
                for (int i = 0; i < indexCategories; i++) {
                    if (arrCategories[i].getCatalogName().equals(catalogName)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Tên danh mục đã tồn tại, vui lòng nhập lại");
                } else {
                    return catalogName;
                }
            } else {
                System.err.println("Tên danh mục tối đa 50 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputDescriptions(Scanner scanner) {
        System.out.println("Nhập mô tả danh mục:");
        return scanner.nextLine();
    }

    public boolean inputCatalogStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái danh mục:");
        do {
            String status = scanner.nextLine();
            if (status.equals("true") || status.equals("false")) {
                return Boolean.parseBoolean(status);
            } else {
                System.err.println("Trạng thái danh mục chỉ nhận true hoặc false, vui lòng nhập lại");
            }
        } while (true);
    }

    public void displayData() {
        System.out.printf("Mã DM: %d - Tên DM: %s - Mô tả: %s - Trạng thái: %s\n",
                this.catalogId, this.catalogName, this.description, this.catalogStatus ? "Hoạt động" : "Không hoạt động");
    }
}
