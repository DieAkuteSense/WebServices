<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Testing Java Script</title>

    <!-- JQuery and AngularJS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular.js"></script>

    <!-- Angular Material -->
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.css" />
    <link rel="stylesheet" href="index.css" />
    <script type="" src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-animate.min.js"></script>
    <script type="" src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-aria.min.js"></script>
    <script type="" src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-messages.min.js"></script>
    <script type="" src="http://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.js"></script>

    <script type="text/javascript">
        var names;

        angular
                .module('frontend', ['ngMaterial'])
                .controller('frontendCtrl', function($scope, $mdDialog, $http) {
                    $scope.types = [
                        {name: 'e10', desc: 'Super (E10)'},
                        {name: 'e5', desc: 'Super (E5)'},
                        {name: 'diesel', desc: 'Diesel'},
                        {name: 'all', desc: 'Alle Kraftstoffe'}
                    ];

                    $scope.sorts = [
                        {name: 'dist', desc: 'Distanz'},
                        {name: 'price', desc: 'Preis'}
                    ]

                    $scope.labels = null;
                    $scope.label = null;

                    $scope.ngInit = function() {
                        var client = new XMLHttpRequest();
                        client.open('POST', '/priceService/services/rest/geoLocatedPrice');
                        client.setRequestHeader('rad', '25');
                        client.setRequestHeader('type', 'diesel');
                        client.setRequestHeader('sort', 'dist');
                        client.send();
                        client.onreadystatechange = function() {
                            if(client.readyState == 4) {
                                var data = client.responseText;
                                $scope.labels = $.parseJSON(data).stations;
                            }
                        };
                    };

                    $scope.geoLocatedPrice = function() {



                        var data = null;
                        var priceClient = new XMLHttpRequest();
                        priceClient.open('POST', '/priceService/services/rest/geoLocatedPrice');
                        priceClient.setRequestHeader('rad', $scope.geoLocated_rad);
                        priceClient.setRequestHeader('type', $scope.geoLocated_type);
                        priceClient.setRequestHeader('sort', $scope.geoLocated_sort);
                        priceClient.send();
                        priceClient.onreadystatechange = function() {
                            if(priceClient.readyState == 4) {
                                data = priceClient.responseText
                                $scope.stations = $.parseJSON(data).stations;
                            }
                        };
                    };

                    $scope.userLocatedPrice = function() {
                        var priceClient = new XMLHttpRequest();
                        priceClient.open('POST', '/priceService/services/rest/userLocatedPrice');
                        priceClient.setRequestHeader('lat', $scope.userLocated_lat);
                        priceClient.setRequestHeader('lon', $scope.userLocated_lon);
                        priceClient.setRequestHeader('rad', $scope.userLocated_rad);
                        priceClient.setRequestHeader('type', $scope.userLocated_type);
                        priceClient.setRequestHeader('sort', $scope.userLocated_sort);
                        priceClient.send();
                        priceClient.onreadystatechange = function() {
                            if(priceClient.readyState == 4) {
                                var data = priceClient.responseText;
                                $scope.stations = $.parseJSON(data).stations;
                            }
                        };
                    };

                    $scope.setGlobalConfig = function(userLocationLat, userLocationLon) {
                        var configClient = new XMLHttpRequest();
                        configClient.open('POST', '/priceService/services/rest/setGlobalConfig');
                        configClient.setRequestHeader('userLocationLat', userLocationLat);
                        configClient.setRequestHeader('userLocationLon', userLocationLon);
                        configClient.send();
                    }
                });
    </script>
</head>
<body ng-app="frontend" ng-controller="frontendCtrl" ng-cloak>
<h1 class="md-headline">Fuel Price Service - Find your gas station</h1>
<h1 class="md-subhead">Web-Service by Andreas Elsemann, Frederik Salzmann, Oliver Scholz</h1>
<md-content>
    <md-tabs class="md-accent" md-dynamic-height>

        <!-- tab for user input located request -->
        <md-tab id="userLocated">
            <md-tab-label>User Located</md-tab-label>
            <md-tab-body>
                <form name="userLocatedForm">
                    <md-grid-list md-cols="5" md-gutter="1em" md-row-height="70px">
                        <md-grid-tile md-rowspan="2" md-colspan="1">
                            <md-input-container>
                                <label>L&auml;ngengrad / Latitude</label>
                                <input required type="text" name="lat" ng-model="$parent.$parent.userLocated_lat" minlength="1" maxlength="12" />
                                <div ng-messages="userLocatedForm.userLocated.$error" role="alert">
                                    <div ng-message-exp="['required', 'minlength', 'maxlength']">
                                        Der L&auml;ngengrad muss eine Zahl sein! / The latitude has to be a number!
                                    </div>
                                </div>
                            </md-input-container>
                        </md-grid-tile>
                        <md-grid-tile md-rowspan="2" md-colspan="1">
                            <md-input-container flex>
                                <label>Breitengrad / Longitude</label>
                                <input required type="text" name="lon" ng-model="$parent.$parent.userLocated_lon" minlength="1" maxlength="12" />
                                <div ng-messages="userLocatedForm.userLocated.$error" role="alert">
                                    <div ng-message-exp="['required', 'minlength', 'maxlength']">
                                        Der Breitengrad muss eine Zahl sein! / The longitude has to be a number!
                                    </div>
                                </div>
                            </md-input-container>
                        </md-grid-tile>
                        <md-grid-tile md-rowspan="3" md-colspan="3">
                            <md-grid-tile-footer>
                                <h3>Response</h3>
                            </md-grid-tile-footer>
                        </md-grid-tile>
                        <md-grid-tile md-rowspan="1" md-colspan="1">
                            <md-slider-container flex style="margin-top: 25px">
                                <md-slider flex md-discrete class="md-primary" ng-model="$parent.$parent.userLocated_rad" name="rad" value="{{rad}}" step="1" min="0" max="25"></md-slider>
                            </md-slider-container>
                        </md-grid-tile>
                        <md-grid-tile md-rowspan="1" md-colspan="1">
                            <md-input-container flex>
                                <label>Kraftstoffart / Sort of fuel</label>
                                <md-select ng-model="$parent.$parent.userLocated_type">
                                    <md-option ng-repeat="type in types" value="{{type.name}}">{{type.desc}}</md-option>
                                </md-select>
                            </md-input-container>
                        </md-grid-tile>
                        <md-grid-tile md-rowspan="1" md-colspan="1">
                            <md-input-container flex>
                                <label>Sortieren nach / Sort by</label>
                                <md-select ng-model="$parent.$parent.userLocated_sort">
                                    <md-option ng-repeat="sort in sorts" value="{{sort.name}}">{{sort.desc}}</md-option>
                                </md-select>
                            </md-input-container>
                        </md-grid-tile>
                        <md-grid-tile md-rowspan="1" md-colspan="1">
                            <md-button class="md-primary md-raised" ng-click="userLocatedPrice()" type="submit">Send request</md-button>
                        </md-grid-tile>
                    </md-grid-list>
                </form>
            </md-tab-body>
        </md-tab>



        <!-- Tab for geo located request -->
        <md-tab id="geoLocated">
            <md-tab-label>Geo Located</md-tab-label>
            <md-tab-body>
                <form name="geoLocatedForm">
                    <md-grid-list md-cols="5" md-gutter="1em" md-row-height="70px">
                        <md-grid-tile md-rowspan="2" md-colspan="1">
                            <md-slider-container flex style="margin-top: 25px">
                                <md-slider flex md-discrete class="md-primary" ng-model="$parent.$parent.geoLocated_rad" name="rad" value="{{rad}}" step="1" min="0" max="25"></md-slider>
                            </md-slider-container>
                        </md-grid-tile>
                        <md-grid-tile md-rowspan="2" md-colspan="1">
                            <md-input-container flex>
                                <label>Kraftstoffart / Sort of fuel</label>
                                <md-select ng-model="$parent.$parent.geoLocated_type">
                                    <md-option ng-repeat="type in types" value="{{type.name}}">{{type.desc}}</md-option>
                                </md-select>
                            </md-input-container>
                        </md-grid-tile>
                        <md-grid-tile md-rowspan="2" md-colspan="3">
                            <md-input-container flex>
                                <label>Ergebnisliste</label>
                                <md-select ng-model="station" placeholder="Select fuel station" style="width: 500px">
                                    <md-option ng-value="station.name" ng-repeat="station in stations">Tankstelle: {{station.name}} // Preis: {{station.price}}</md-option>
                                </md-select>
                            </md-input-container>
                        </md-grid-tile>
                        <md-grid-tile md-rowspan="1" md-colspan="1">
                            <md-input-container flex>
                                <label>Sortieren nach / Sort by</label>
                                <md-select ng-model="$parent.$parent.geoLocated_sort">
                                    <md-option ng-repeat="sort in sorts" value="{{sort.name}}">{{sort.desc}}</md-option>
                                </md-select>
                            </md-input-container>
                        </md-grid-tile>
                        <md-grid-tile md-rowspan="1" md-colspan="1">
                            <md-button class="md-primary md-raised" ng-click="geoLocatedPrice()" type="submit">Send request</md-button>
                        </md-grid-tile>
                        <md-grid-tile md-rowspan="1" md-colspan="3">
                            <p>When the user clicks the button, the RESTful service is invoked. This service requests the users current location by using the Google
                                Geolocation API. With the received data (latitude and longitude) the service requests the current fuel prices in the area.</p>
                        </md-grid-tile>
                    </md-grid-list>
                </form>
            </md-tab-body>
        </md-tab>
    </md-tabs>
</md-content>

<br />
<hr />
<br />

<div style="background-color: #eff0f1; padding: 20px">
    <b>Geo Located request. Result loaded in drop down list. Request is invoked when drop down list is opened.</b>
    <md-select ng-model="label" placeholder="Select fuel station" md-on-open="ngInit()" style="width: 500px">
        <md-option ng-value="label.name" ng-repeat="label in labels">Tankstelle: {{label.name}} // Preis: {{label.price}}</md-option>
    </md-select>
</div>


</body>
</html>