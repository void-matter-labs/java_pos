package crud.app.sidebar;

import java.util.Arrays;
import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import crud.app.sidebar.components.FxCustomButton;
import crud.app.sidebar.dtos.ButtonConfig;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class SidebarModule extends AbstractModule {
    @Override
    protected void configure() {
        // Bindings for sidebar module
    }

    @Provides
    public List<ButtonConfig> provideButtonConfigs() {
        return Arrays.asList(
            new ButtonConfig("button1", "Button 1", true),
            new ButtonConfig("button2", "Button 2", false),
            new ButtonConfig("button3", "Button 3", false)
        );
    }

    @Provides
    @Singleton
    public EventHandler<MouseEvent> provideClickHandler() {
      return event -> {
        FxCustomButton item = (FxCustomButton) event.getSource();
        System.out.println("Mouse clicked on: " + item.getCustomId());
      };
    }
}
