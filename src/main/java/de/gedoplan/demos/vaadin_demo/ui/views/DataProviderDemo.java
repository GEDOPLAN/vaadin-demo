package de.gedoplan.demos.vaadin_demo.ui.views;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.HeaderRow;

import de.gedoplan.demos.vaadin_demo.business.country.Country;
import de.gedoplan.demos.vaadin_demo.business.country.CountryService;
import de.gedoplan.demos.vaadin_demo.ui.navigation.ViewIDs;

@CDIView(ViewIDs.dataProvider)
public class DataProviderDemo extends VerticalLayout implements View {

  @Inject
  private CountryService countryService;

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent event) {

    Mapfilter mapfilter = new Mapfilter();

    Grid<Country> gridLazyFilter = new Grid<>(Country.class);
    gridLazyFilter.setCaption("Lazy Data Provider");
    gridLazyFilter.setWidth(100, Unit.PERCENTAGE);
    ConfigurableFilterDataProvider<Country, Void, Mapfilter> dataProvider = DataProvider.<Country, Mapfilter> fromFilteringCallbacks(
        (query) -> countryService.loadCountries(query.getOffset(), query.getLimit(), mapSortOrder(query.getSortOrders()), query.getFilter().get().filters).stream(),
        (query) -> countryService.countCountries(query.getFilter().get().filters).intValue()).withConfigurableFilter();
    dataProvider.setFilter(mapfilter);
    gridLazyFilter.setDataProvider(dataProvider);
    HeaderRow header = gridLazyFilter.prependHeaderRow();
    gridLazyFilter.getColumns().forEach(col -> {
      header.getCell(col).setComponent(new TextField(e -> {
        mapfilter.setFilter(col.getId(), e.getValue());
        dataProvider.refreshAll();
      }));
    });
    addComponent(gridLazyFilter);
  }

  static class Mapfilter {
    private Map<String, String> filters = new HashMap<>();

    public Map<String, String> getFilters() {
      return filters;
    }

    public void setFilter(String key, String value) {
      if (value == null || value.trim().isEmpty()) {
        filters.remove(key);
      } else {
        filters.put(key, value);
      }
    }
  }

  protected Map<String, Boolean> mapSortOrder(List<QuerySortOrder> sortOrder) {
    return sortOrder.stream().collect(Collectors.toMap(s -> s.getSorted(), s -> s.getDirection() != SortDirection.ASCENDING));
  }
}
