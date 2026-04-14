package tests.admin;

import base.AdminAuthBaseTest;
import config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.AdminVASPage;

import java.time.Duration;

public class AdminVASTest extends AdminAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrlAdmin() + "/vas");
    }

    @Test(description = "VS-01 Trang Quản lý Dịch vụ hiển thị")
    public void pageDisplayed() {
        AdminVASPage page = new AdminVASPage(getDriver());
        Assert.assertTrue(page.isPageDisplayed(), "Trang Quản lý Dịch vụ phải hiển thị");
    }

    @Test(description = "VS-02 Tiêu đề trang VAS đúng")
    public void pageTitle() {
        AdminVASPage page = new AdminVASPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Dịch vụ") || title.contains("VAS") || title.contains("BookingHub"),
                "Tiêu đề phải chứa Dịch vụ/VAS/BookingHub");
    }

    @Test(description = "VS-03 Heading Quản lý dịch vụ hiển thị")
    public void headingDisplayed() {
        AdminVASPage page = new AdminVASPage(getDriver());
        Assert.assertTrue(page.headingDisplayed(), "Heading trang dịch vụ hiển thị");
    }

    @Test(description = "VS-04 Nút thêm dịch vụ và bảng hiển thị")
    public void addButtonAndTable() {
        AdminVASPage page = new AdminVASPage(getDriver());
        Assert.assertTrue(page.isAddButtonDisplayed(), "Nút thêm dịch vụ hiển thị");
        Assert.assertTrue(page.isTableDisplayed(), "Bảng dịch vụ hiển thị");
    }

    @Test(description = "VS-05 Tìm kiếm dịch vụ theo từ khóa")
    public void searchByKeyword() {
        AdminVASPage page = new AdminVASPage(getDriver());
        page.typeSearchKeyword("test");
        page.clickSearchButton();
        Assert.assertTrue(page.isPageDisplayed(), "Trang vẫn ổn định");
    }

    @Test(description = "VS-06 Tìm kiếm chuỗi rỗng")
    public void searchEmpty() {
        AdminVASPage page = new AdminVASPage(getDriver());
        page.typeSearchKeyword("");
        page.clickSearchButton();
        Assert.assertTrue(page.isPageDisplayed(), "Trang vẫn ổn định");
    }

    @Test(description = "VS-07 Gửi thông tin trống modal vẫn mở")
    public void validation_submitEmpty_modalStaysOpen() {
        AdminVASPage page = new AdminVASPage(getDriver());
        page.clickAdd();
        Assert.assertTrue(page.isModalDisplayed(), "Modal phải mở");
        page.clickSubmitOnly();
        new WebDriverWait(getDriver(), Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-modal']")));
        Assert.assertTrue(page.isModalDisplayed(), "Gửi thông tin trống không đóng modal");
    }

    @Test(description = "VS-08 Create - Thêm dịch vụ mới")
    public void crud_create() {
        AdminVASPage page = new AdminVASPage(getDriver());
        String name = "Dịch vụ Test " + System.currentTimeMillis();
        String type = "luggage";
        int price = 50000;

        page.clickAdd();
        Assert.assertTrue(page.isModalDisplayed(), "Modal thêm dịch vụ phải mở");
        page.fillForm(name, type, price);
        page.submitForm();

        Assert.assertTrue(page.tableContainsName(name),
                "Sau khi thêm, bảng phải chứa tên dịch vụ: " + name);
    }

    @Test(description = "VS-09 Update - Chỉnh sửa dịch vụ", dependsOnMethods = "crud_create")
    public void crud_update() {
        AdminVASPage page = new AdminVASPage(getDriver());
        if (page.getTableRowCount() == 0) {
            Assert.fail("Cần có ít nhất 1 dịch vụ để sửa.");
        }
        String updated = "Dịch vụ Updated " + System.currentTimeMillis();
        page.clickEditFirstRow();
        Assert.assertTrue(page.isModalDisplayed(), "Modal sửa phải mở");
        page.fillForm(updated, "luggage", 75000);
        page.submitForm();
        Assert.assertTrue(page.tableContainsName(updated), "Bảng phải chứa tên sau cập nhật");
    }

    @Test(description = "VS-10 Delete - Xóa dịch vụ", dependsOnMethods = "crud_update")
    public void crud_delete() {
        AdminVASPage page = new AdminVASPage(getDriver());
        int countBefore = page.getTableRowCount();
        if (countBefore == 0) {
            Assert.fail("Cần có ít nhất 1 dịch vụ để xóa.");
        }

        page.clickDeleteFirstRow();
        page.confirmDelete();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        wait.until(d -> page.getTableRowCount() == countBefore - 1);
        Assert.assertEquals(page.getTableRowCount(), countBefore - 1,
                "Sau khi xóa, số dòng phải giảm 1");
    }
}
