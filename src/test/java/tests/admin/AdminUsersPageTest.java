package tests.admin;

import base.AdminAuthBaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.AdminUsersPage;

/**
 * Quản lý Người dùng: bảng, CRUD qua modal.
 */
public class AdminUsersPageTest extends AdminAuthBaseTest {

    private static String createdUsername;

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrlAdmin() + "/users");
    }

    @Test(description = "US-01 Trang Quản lý Người dùng hiển thị")
    public void usersPageDisplayed() {
        AdminUsersPage page = new AdminUsersPage(getDriver());
        Assert.assertTrue(page.isPageDisplayed(), "Trang Quản lý Người dùng phải hiển thị");
    }

    @Test(description = "US-02 Tiêu đề trang người dùng đúng")
    public void usersPageTitle() {
        AdminUsersPage page = new AdminUsersPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Người dùng") || title.contains("BookingHub"),
                "Tiêu đề phải chứa Người dùng/BookingHub");
    }

    @Test(description = "US-03 Heading Quản lý Người dùng hiển thị")
    public void usersHeadingDisplayed() {
        AdminUsersPage page = new AdminUsersPage(getDriver());
        Assert.assertTrue(page.headingDisplayed(), "Heading hiển thị");
    }

    @Test(description = "US-04 Nút Thêm người dùng hiển thị")
    public void addButtonDisplayed() {
        AdminUsersPage page = new AdminUsersPage(getDriver());
        Assert.assertTrue(page.isAddButtonDisplayed(), "Nút thêm hiển thị");
    }

    @Test(description = "US-05 Bảng người dùng hiển thị")
    public void tableDisplayed() {
        AdminUsersPage page = new AdminUsersPage(getDriver());
        Assert.assertTrue(page.isTableDisplayed(), "Bảng hiển thị");
    }

    @Test(description = "US-06 Mở modal thêm người dùng")
    public void openCreateModal() {
        AdminUsersPage page = new AdminUsersPage(getDriver());
        page.clickAdd();
        Assert.assertTrue(page.isModalDisplayed(), "Modal thêm mở");
        page.clickModalCancel();
    }

    @Test(description = "US-07 Create - Thêm người dùng mới")
    public void crud_create() {
        AdminUsersPage page = new AdminUsersPage(getDriver());
        String suffix = String.valueOf(System.currentTimeMillis());
        createdUsername = "auto_user_" + suffix;
        String email = createdUsername + "@test.local";

        page.clickAdd();
        page.fillCreateForm(createdUsername, email, "Người test", "staff", "Test123456");
        page.clickModalSave();

        new org.openqa.selenium.support.ui.WebDriverWait(getDriver(), java.time.Duration.ofSeconds(15))
                .until(d -> page.tableContainsUsername(createdUsername));
        Assert.assertTrue(page.tableContainsUsername(createdUsername), "Bảng có người dùng vừa tạo");
    }

    @Test(description = "Update - Sửa tên người dùng", dependsOnMethods = "crud_create")
    public void crud_update() {
        AdminUsersPage page = new AdminUsersPage(getDriver());
        if (createdUsername == null || !page.tableContainsUsername(createdUsername)) {
            Assert.fail("Cần người dùng test từ bước tạo mới.");
        }
        page.clickEditRowContainingUsername(createdUsername);
        Assert.assertTrue(page.isModalDisplayed(), "Modal sửa mở");
        String newName = "Tên mới " + System.currentTimeMillis();
        page.updateNameInModal(newName);
        page.clickModalSave();

        org.openqa.selenium.support.ui.WebDriverWait wait =
                new org.openqa.selenium.support.ui.WebDriverWait(getDriver(), java.time.Duration.ofSeconds(15));
        wait.until(d -> d.findElements(org.openqa.selenium.By.xpath("//*[contains(.,'" + newName + "')]")).stream()
                .anyMatch(org.openqa.selenium.WebElement::isDisplayed));
        Assert.assertTrue(
                getDriver().findElements(org.openqa.selenium.By.xpath("//*[contains(.,'" + newName + "')]")).stream()
                        .anyMatch(org.openqa.selenium.WebElement::isDisplayed),
                "Giao diện hiển thị tên mới");
    }

    @Test(description = "US-09 Delete - Xóa người dùng test", dependsOnMethods = "crud_update")
    public void crud_delete() {
        AdminUsersPage page = new AdminUsersPage(getDriver());
        if (createdUsername == null) {
            Assert.fail("Thiếu tài khoản test.");
        }
        page.clickDeleteRowContainingUsername(createdUsername);
        page.confirmDelete();
        org.openqa.selenium.support.ui.WebDriverWait wait =
                new org.openqa.selenium.support.ui.WebDriverWait(getDriver(), java.time.Duration.ofSeconds(15));
        wait.until(d -> !page.tableContainsUsername(createdUsername));
        Assert.assertFalse(page.tableContainsUsername(createdUsername), "Người dùng đã bị xóa khỏi bảng");
    }
}
