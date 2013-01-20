/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package pennapps2013.where2meet.client;

import java.util.ArrayList;
import java.util.HashMap;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Where2Meet implements EntryPoint {
	
    private VerticalPanel mainPanel;
    private FlexTable addressFlexTable;
    private HorizontalPanel addPanel;
    private TextBox newAddressTextBox;
    private Button addButton;
    private ArrayList <String> addresses = new ArrayList<String>();
    private Label where2meetLabel;
    private Button locateButton;
    
    private Geocoder geocoder;
    
    class GeoRunner implements LatLngCallback {
    	
    	private LatLng[] points;
    	private int i;
    	
    	public GeoRunner(int numpoints) {
    		this.points = new LatLng[numpoints];
    		this.i = 0;
    	}
    	
    	public void run() {
    		if (i < points.length)
    			geocoder.getLatLng(addressFlexTable.getText(i + 1, 0), this);
    		else {
    			// points is now full
    			for (int j = 0; j < points.length; j++)
    				System.out.println(points[j]);
    			//searchPlaces(SEC.findCenter(points));
    		}
    	}

		@Override
		public void onFailure() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(com.google.gwt.maps.client.geom.LatLng point) {
			points[i++] = new LatLng(point.getLatitude(), point.getLongitude());
			run();
		}
    	
    }
    
    public void searchPlaces(Circle circle) {
    	YelpServiceAsync s = GWT.create(YelpService.class);
    	AsyncCallback<ArrayList<Business>> cb = new AsyncCallback<ArrayList<Business>>() {
    		public void onFailure(Throwable caught) {
    			System.out.println(caught.toString());
    			if (caught instanceof SearchException) {
    				
    			}
    		}
    		
    		public void onSuccess(ArrayList<Business> result) {
    			System.out.println("success");
    			System.out.println(result.toString());
    		}
		};
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("term", "five guys");
		params.put("limit", "10");
		params.put("ll", 39.951791 + "," + -75.190211);
		params.put("radius_filter", Integer.toString(15000));
		
		s.getPlaces(params, cb);
    }
    
    public void onModuleLoad() {
        RootPanel rootPanel = RootPanel.get();

        mainPanel = new VerticalPanel();
        mainPanel.setStylePrimaryName("gwt-Panel-main");
        mainPanel.setStyleName("body");
        rootPanel.add(mainPanel, 10, 10);
        mainPanel.setSize("250px", "200px");

        // where2meetLabel = new Label("where2meet");
        // where2meetLabel.setStylePrimaryName("gwt-Label-where2meet");
        // where2meetLabel.setStyleName("gwt-Label-where2meet");
        // mainPanel.add(where2meetLabel);

        addressFlexTable = new FlexTable();
        addressFlexTable.setText(0, 0, "Address");
        addressFlexTable.setText(0, 1, "Remove");

        // // Add styles to elements in the address list table.
        // addressFlexTable.setCellPadding(6);
        // addressFlexTable.getRowFormatter().addStyleName(0, "addressListHeader");
        // addressFlexTable.addStyleName("addressList");
        // addressFlexTable.getCellFormatter().addStyleName(0, 1, "addressListRemoveColumn");
        addressFlexTable.setStyleName("table");
        addressFlexTable.setStylePrimaryName("table");

        mainPanel.add(addressFlexTable);
        mainPanel.setCellHorizontalAlignment(addressFlexTable, HasHorizontalAlignment.ALIGN_CENTER);

        addPanel = new HorizontalPanel();
        mainPanel.add(addPanel);
        mainPanel.setCellHorizontalAlignment(addPanel, HasHorizontalAlignment.ALIGN_CENTER);

        newAddressTextBox = new TextBox();
        newAddressTextBox.addKeyPressHandler(new KeyPressHandler() {
            public void onKeyPress(KeyPressEvent event) {
                if (event.getCharCode() == KeyCodes.KEY_ENTER){
                    addAddress();
                }
            }
        });
        newAddressTextBox.setFocus(true);
        addPanel.add(newAddressTextBox);

        addButton = new Button("New button");
        addButton.setStylePrimaryName("btn");
        addButton.setStyleName("btn");
        addButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                addAddress();
            }
        });
        addButton.setText("Add");
        addPanel.add(addButton);
        // addPanel.addStyleName("addPanel");
        
        locateButton = new Button("New button");
        locateButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int rowcount = addressFlexTable.getRowCount();
				GeoRunner g = new GeoRunner(rowcount - 1);
				g.run();				
			}
		});
        //com.google.gwt.maps.client.geocode.Geocoder a;
        //a.getLatLng(address, callback);
        locateButton.setStylePrimaryName("btn");
        locateButton.setStyleName("btn");
        locateButton.setText("Locate!");
        mainPanel.add(locateButton);
        mainPanel.setCellHorizontalAlignment(locateButton, HasHorizontalAlignment.ALIGN_CENTER);

        /*
        Maps.loadMapsApi("AIzaSyDEY3W8pwAUZfWSNEVSTc_RRCU0oGN29gg",
        		"3", false, new Runnable() {
					@Override
					public void run() {
						System.out.println(Maps.isLoaded());
						geocoder = new Geocoder();
					}
				});
				
       	*/
        System.out.println(Maps.isLoaded());
        geocoder = new Geocoder();
    }
    private void addAddress() {
        final String address = newAddressTextBox.getText().trim();
        newAddressTextBox.setFocus(true);

        // Address must be between 1 and 10 chars that are numbers, letters, or dots.
        if (!address.matches("^[0-9a-zA-Z ]{1,100}$")) {
          Window.alert("'" + address + "' is not a valid address.");
          newAddressTextBox.selectAll();
          return;
        }

        newAddressTextBox.setText("");

        // Don't add the address if it's already in the table.
        if (addresses.contains(address))
            return;

        // Add the address to the table.
        int row = addressFlexTable.getRowCount();
        addresses.add(address);
        addressFlexTable.setText(row, 0, address);
        //addressFlexTable.getCellFormatter().addStyleName(row, 1, "addressListRemoveColumn");

        // Add a button to remove this address from the table.
        Button removeAddress = new Button("x");
        //removeAddress.addStyleDependentName("remove");
        removeAddress.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
            int removedIndex = addresses.indexOf(address);
            addresses.remove(removedIndex);
            addressFlexTable.removeRow(removedIndex + 1);
        }
        });
        addressFlexTable.setWidget(row, 1, removeAddress);
    }
}
