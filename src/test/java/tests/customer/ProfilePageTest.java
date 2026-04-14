package tests.customer;

import base.CustomerAuthBaseTest;
import config.Config;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ProfilePage;

import java.time.Duration;

public class ProfilePageTest extends CustomerAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrl() + "/profile");
    }

    @Test(description = "PF-01 Trang thông tin cá nhân hiển thị sau khi đăng nhập")
    public void profilePageLoads() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(d -> {
                    ProfilePage p = new ProfilePage(d);
                    return p.isPageLoaded() && !p.isLoadingState();
                });
        ProfilePage page = new ProfilePage(getDriver());
        Assert.assertTrue(page.isPageLoaded(), "Trang thông tin cá nhân hiển thị");
    }

    @Test(description = "PF-02 Tiêu đề chứa Thông tin hoặc BookingHub")
    public void profilePageTitle() {
        ProfilePage page = new ProfilePage(getDriver());
        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(d -> !new ProfilePage(d).isLoadingState());
        String title = page.getPageTitle();
        Assert.assertTrue(title.contains("BookingHub") || title.contains("cá nhân") || title.toLowerCase().contains("profile"),
                "Tiêu đề hợp lệ");
    }

    @Test(description = "PF-03 URL chứa profile")
    public void profileUrl() {
        Assert.assertTrue(getDriver().getCurrentUrl().contains("profile"), "URL profile");
    }

    @Test(description = "PF-04 Heading Thông tin cá nhân hiển thị")
    public void headingDisplayed() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(d -> !new ProfilePage(d).isLoadingState());
        ProfilePage page = new ProfilePage(getDriver());
        Assert.assertTrue(page.isHeadingDisplayed(), "Heading hiển thị");
    }

    @Test(description = "PF-05 Form có trường dữ liệu")
    public void formHasEditableFields() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(d -> !new ProfilePage(d).isLoadingState());
        ProfilePage page = new ProfilePage(getDriver());
        Assert.assertTrue(page.hasNameField(), "Ô họ tên");
        Assert.assertTrue(page.hasEmailField(), "Ô email");
        Assert.assertTrue(page.hasPhoneField(), "Ô SĐT");
    }

    @Test(description = "PF-06 Nút Lưu thay đổi hiển thị")
    public void submitButtonDisplayed() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(d -> !new ProfilePage(d).isLoadingState());
        ProfilePage page = new ProfilePage(getDriver());
        Assert.assertTrue(page.hasSubmitSaveButton(), "Nút lưu hiển thị");
    }

    @Test(description = "PF-07 Không bị điều hướng về khi đã đăng nhập")
    public void staysOnProfileWhenAuthenticated() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(d -> !d.getCurrentUrl().contains("/login"));
        Assert.assertFalse(getDriver().getCurrentUrl().contains("/login"),
                "Không bị điều hướng về login");
    }

    @Test(description = "PF-08 Có thể điền lại họ tên")
    public void canFillNameField() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(d -> !new ProfilePage(d).isLoadingState());
        ProfilePage page = new ProfilePage(getDriver());
        if (page.hasNameField()) {
            page.fillName("Test Name Selenium");
            Assert.assertTrue(page.hasNameField(), "Ô tên vẫn hiển thị sau khi điền");
        }
    }

    @Test(description = "PF-09 Trang hồ sơ cá nhân có form")
    public void hasFormSection() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(d -> !new ProfilePage(d).isLoadingState());
        ProfilePage page = new ProfilePage(getDriver());
        Assert.assertTrue(page.hasSubmitSaveButton() && page.hasNameField(),
                "Form chỉnh sửa hiển thị");
    }

    @Test(description = "PF-10 Kiểm tra tiêu đề không rỗng")
    public void documentTitleExists() {
        Assert.assertFalse(getDriver().getTitle().isEmpty(), "Có tiêu đề");
    }
}
