package ra.run;

import ra.entity.Categories;
import ra.entity.Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ShopManagement {
    Categories[] arrCategories = new Categories[100];
    Product[] arrProducts = new Product[100];
    int indexCategories = 0;
    int indexProduct = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShopManagement shopManagement = new ShopManagement();
        do {
            System.out.println("************SHOP MENU************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    shopManagement.displayCategoriesMenu(scanner, shopManagement);
                    break;
                case 2:
                    shopManagement.displayProductMenu(scanner, shopManagement);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-3");
            }
        } while (true);
    }

    public void displayCategoriesMenu(Scanner scanner, ShopManagement shopManagement) {
        boolean isExit = true;
        do {
            System.out.println("*************CATEGORIES MENU************");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin các danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    shopManagement.inputListCategories(scanner);
                    break;
                case 2:
                    shopManagement.displayListCategories();
                    break;
                case 3:
                    shopManagement.updateCategories(scanner);
                    break;
                case 4:
                    shopManagement.deleteCategories(scanner);
                    break;
                case 5:
                    shopManagement.updateCategorieStatus(scanner);
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
        } while (isExit);
    }

    public void displayProductMenu(Scanner scanner, ShopManagement shopManagement) {
        boolean isExit = true;
        do {
            System.out.println("*******************PRODUCT MANAGEMENT*****************\n" +
                    "1. Nhập thông tin các sản phẩm\n" +
                    "2. Hiển thị thông tin các sản phẩm\n" +
                    "3. Sắp xếp các sản phẩm theo giá\n" +
                    "4. Cập nhật thông tin sản phẩm theo mã sản phẩm\n" +
                    "5. Xóa sản phẩm theo mã sản phẩm\n" +
                    "6. Tìm kiếm các sản phẩm theo tên sản phẩm\n" +
                    "7. Tìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn\n" +
                    "phím)\n" +
                    "8. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    shopManagement.inputListProduct(scanner);
                    break;
                case 2:
                    shopManagement.displayListProduct();
                    break;
                case 3:
                    shopManagement.sortProductByPrice();
                    break;
                case 4:
                    shopManagement.updateProduct(scanner);
                    break;
                case 5:
                    shopManagement.deleteProductById(scanner);
                    break;
                case 6:
                    shopManagement.searchProductByName(scanner);
                    break;
                case 7:
                    shopManagement.searchProductByRange(scanner);
                    break;
                case 8:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-8");
            }
        } while (isExit);
    }

    private void searchProductByRange(Scanner scanner) {
        System.out.println("Nhập khoảng giá bạn muốn tìm kiếm (a – b)");
        System.out.print("Nhập giá thấp nhất (a): ");
        float priceLow = Float.parseFloat(scanner.nextLine());
        System.out.print("Nhập giá cao nhất (b): ");
        float priceHigh = Float.parseFloat(scanner.nextLine());
        boolean foundProduct1 = false;
        for (Product pro : arrProducts) {
            if (pro != null && pro.getPrice() >= priceLow && pro.getPrice() <= priceHigh) {
                pro.displayData(arrCategories, indexCategories);
                foundProduct1 = true;
            }
        }
        if (!foundProduct1) {
            System.out.println("Không tìm thấy sản phẩm nào trong khoảng giá từ " + priceLow + " đến " + priceHigh);
        }
    }

    private void deleteProductById(Scanner scanner) {
        System.out.println("mời bạn nhập id sp cần delete");
        String idDelete = scanner.nextLine();
        boolean check = false;
        for (int i = 0; i < indexProduct; i++) {
            if (arrProducts[i].getProductId().equals(idDelete)) {
                check = true;
            }
            for (int j = i; j < indexProduct - 1; j++) {
                arrProducts[j] = arrProducts[j + 1];
            }
            indexProduct--;
            System.out.println("sp đã xóa thành công");
            break;

        }
        if (!check) {
            System.out.println("không tìm thấy id sp cần xóa " + idDelete);

        }
    }

    private void searchProductByName(Scanner scanner) {
        System.out.println("mời bạn nhập tên cần tìm kiếm ");
        String nameSearch = scanner.nextLine();
        boolean check = false;
        for (int i = 0; i < indexProduct; i++) {
            if (arrProducts[i].getProductName().toLowerCase().contains(nameSearch.toLowerCase())) {
                check = true;
                arrProducts[i].displayData(arrCategories, indexCategories);
                break;
            }
        }
        if (!check) {
            System.out.println("không tìm thấy tên sản phẩm");
        }
    }

    //thêm danh mục
    public void inputListCategories(Scanner scanner) {
        System.out.println("Nhập số danh mục cần nhập thông tin:");
        int numberOfCategories = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfCategories; i++) {
            arrCategories[indexCategories] = new Categories();
            arrCategories[indexCategories].inputData(scanner, arrCategories, indexCategories);
            indexCategories++;
        }
    }

    //thêm sản phẩm
    public void inputListProduct(Scanner scanner) {
        System.out.println("Nhập số sản phẩm cần nhập thông tin:");
        int numberOfProduct = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfProduct; i++) {
            arrProducts[indexProduct] = new Product();
            arrProducts[indexProduct].inputData(scanner, arrProducts, indexProduct, arrCategories, indexCategories);
            indexProduct++;
        }
    }

    // hiển thi dnah mục
    public void displayListCategories() {
        for (int i = 0; i < indexCategories; i++) {
            arrCategories[i].displayData();
        }
    }

    // hiển thị sản phẩm
    public void displayListProduct() {
        for (int i = 0; i < indexProduct; i++) {
            arrProducts[i].displayData(arrCategories, indexCategories);
        }
    }

    // update category
    public void updateCategories(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần cập nhật:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int indexUpdate = getIndexById(catalogId);
        if (indexUpdate >= 0) {
            boolean isExit = true;
            do {
                System.out.println("1. Cập nhật tên danh mục");
                System.out.println("2. Cập nhật mô tả");
                System.out.println("3. Cập nhật trạng thái");
                System.out.println("4. Thoát");
                System.out.println("Lựa chọn của bạn:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        arrCategories[indexUpdate].setCatalogName(scanner.nextLine());
                        break;
                    case 2:
                        arrCategories[indexUpdate].setDescription(scanner.nextLine());
                        break;
                    case 3:
                        arrCategories[indexUpdate].setCatalogStatus(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    case 4:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-4");
                }
            } while (isExit);
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    //update product
    public void updateProduct(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm cần cập nhật:");
        String productId = scanner.nextLine();
        for (int i = 0; i < indexProduct; i++) {
            if (arrProducts[i].getProductId().equals(productId)) {
                boolean isExit = true;
                do {
                    System.out.println("1. Cập nhật tên sản phẩm");
                    System.out.println("2. Cập nhật mô tả");
                    System.out.println("3. Cập nhật trạng thái");
                    System.out.println("4. Cập nhật giá");
                    System.out.println("5. Cập nhật ngay nhap hang");
                    System.out.println("6. Thoát");
                    System.out.println("Lựa chọn của bạn:");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            arrProducts[i].setProductName(scanner.nextLine());
                            break;
                        case 2:
                            arrProducts[i].setDescription(scanner.nextLine());
                            break;
                        case 3:
                            arrProducts[i].setProductStatus(Integer.parseInt(scanner.nextLine()));
                            break;
                        case 4:
                            arrProducts[i].setPrice(Float.parseFloat(scanner.nextLine()));
                            break;
                        case 5:
                            SimpleDateFormat sdf = new SimpleDateFormat();
                            try {
                                arrProducts[i].setCreated(sdf.parse(scanner.nextLine()));
                            } catch (ParseException e) {
                                System.err.println("ngày tháng nhập không đúng định dạng dd/MM/yyyy");
                                ;
                            }
                            break;
                        case 6:
                            isExit = false;
                            break;
                        default:
                            System.err.println("Vui lòng chọn từ 1-4");
                    }
                } while (isExit);
            } else {
                System.err.println("Mã danh mục không tồn tại");
            }

        }

    }

    public void sortProductByPrice() {
        System.out.println("mảng trước khi sắp xếp ");
        for (int i = 0; i < indexProduct; i++) {
            arrProducts[i].displayData(arrCategories, indexCategories);
        }

        for (int i = 0; i < indexProduct - 1; i++) {
            for (int j = i + 1; j < indexProduct; j++) {
                if (arrProducts[i].getPrice() > (arrProducts[j].getPrice())) {
                    Product temp = arrProducts[i];
                    arrProducts[i] = arrProducts[j];
                    arrProducts[j] = temp;
                }
            }
        }
        System.out.println("Mảng sản phẩm sắp xếp theo giá là: ");
        for (int i = 0; i < indexProduct; i++) {
            arrProducts[i].displayData(arrCategories, indexCategories);
        }

    }


    public int getIndexById(int catalogId) {
        for (int i = 0; i < indexCategories; i++) {
            if (arrCategories[i].getCatalogId() == catalogId) {
                return i;
            }
        }
        return -1;
    }

    public void deleteCategories(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần xóa:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int indexDelete = getIndexById(catalogId);
        if (indexDelete >= 0) {
            //Kiểm tra danh mục có sản phẩm chưa
            boolean isExistProduct = false;
            for (int i = 0; i < indexProduct; i++) {
                if (arrProducts[i].getCatalogId() == catalogId) {
                    isExistProduct = true;
                    break;
                }
            }
            if (isExistProduct) {
                System.err.println("Danh mục đang chứa sản phẩm, không thể xóa");
            } else {
                //Thực hiện xóa
                for (int i = indexDelete + 1; i < indexCategories; i++) {
                    arrCategories[i - 1] = arrCategories[i];
                }
                //Gán phần tử cuối là null
                arrCategories[indexCategories - 1] = null;
                //giảm chỉ số đi 1
                indexCategories--;
            }
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public void updateCategorieStatus(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần cập nhật trạng thái:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int indexUpdateStatus = getIndexById(catalogId);
        if (indexUpdateStatus >= 0) {
            arrCategories[indexUpdateStatus].setCatalogStatus(!arrCategories[indexUpdateStatus].isCatalogStatus());
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }
}
