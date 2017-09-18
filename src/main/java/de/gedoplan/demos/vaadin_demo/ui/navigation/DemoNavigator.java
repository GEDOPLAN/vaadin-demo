package de.gedoplan.demos.vaadin_demo.ui.navigation;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.cdi.NormalUIScoped;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.UI;

import de.gedoplan.demos.vaadin_demo.ui.DemoUi;
import de.gedoplan.demos.vaadin_demo.ui.views.ErrorView;

@NormalUIScoped
public class DemoNavigator {

  @Inject
  private DemoUi ui;

  @Inject
  private CDIViewProvider viewProvider;

  private Navigator navigator;

  @PostConstruct
  protected void init() {
    navigator = new Navigator(ui, ui.getMainContent());
    navigator.addProvider(viewProvider);
    navigator.setErrorView(ErrorView.class);
  }

  public void navigate(@Observes @Navigation String viewId) {
    navigator.navigateTo(viewId);
  }
  
  public void init(@Observes UI ui) {
	  
  }

}