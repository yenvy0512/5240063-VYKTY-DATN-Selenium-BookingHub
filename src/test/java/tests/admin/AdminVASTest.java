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

/**
 * Test VAS (Dịch vụ): trang, CRUD (Create/Delete), Validation - gộp 1 file.
 */
public class AdminVASTest extends AdminAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrlAdmin() + "/vas");
    }

    // --- Trang ---
    @Test(description = "Trang Quản lý Dịch vụ hiển thị")
    public void pageDisplayed() {
        AdminVASPage page = new AdminVASPage(getDriver());
        Assert.assertTrue(page.isPageDisplayed(), "Trang Quản lý Dịch vụ phải hiển thị");
    }

    @Test(description = "Title trang VAS đúng")
    public void pageTitle() {
        AdminVASPage page = new AdminVASPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Dịch vụ") || title.contains("VAS") || title.contains("BookingHub"),
                "Title phải chứa Dịch vụ/VAS/BookingHub");
    }

    // --- Validation ---
    @Test(description = "Submit form trống modal vẫn mở")
    public void validation_submitEmpty_modalStaysOpen() {
        AdminVASPage page = new AdminVASPage(getDriver());
        page.clickAdd();
        Assert.assertTrue(page.isModalDisplayed(), "Modal phải mở");
        page.clickSubmitOnly();
        new WebDriverWait(getDriver(), Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-modal']")));
        Assert.assertTrue(page.isModalDisplayed(), "Submit form trống phải không đóng modal");
    }

    // --- CRUD ---
    @Test(description = "Create - Thêm dịch vụ mới")
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

    @Test(description = "Delete - Xóa dịch vụ", dependsOnMethods = "crud_create")
    public void crud_delete() {
        AdminVASPage page = new AdminVASPage(getDriver());
        int countBefore = page.getTableRowCount();
        if (countBefore == 0) {
            Assert.fail("Cần có ít nhất 1 dịch vụ để test Delete.");
        }

        page.clickDeleteFirstRow();
        page.confirmDelete();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        wait.until(d -> page.getTableRowCount() == countBefore - 1);
        Assert.assertEquals(page.getTableRowCount(), countBefore - 1,
                "Sau khi xóa, số dòng phải giảm 1");
    }
}
