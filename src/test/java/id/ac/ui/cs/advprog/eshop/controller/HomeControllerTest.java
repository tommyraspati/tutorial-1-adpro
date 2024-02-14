package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.ui.Model;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @Test
    void testHomePage() {
        Model model = mock(Model.class); // Create a mock Model object
        String viewName = homeController.homePage(model);

        assertEquals("HomePage", viewName);
    }
}
