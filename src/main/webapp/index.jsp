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
                .module('selectStation', ['ngMaterial'])
                .controller('selectStationContr', function($scope, $mdDialog) {
                    $scope.sorts = [
                        {name: 'e10', desc: 'Super (E10)'},
                        {name: 'e5', desc: 'Super (E5)'},
                        {name: 'diesel', desc: 'Diesel'},
                        {name: 'all', desc: 'Alle Kraftstoffe'}
                    ];

                    $scope.labels = null;
                    $scope.label = null;
                    $scope.ngInit = function() {
                       return $.post("services/rest/requestPriceCurrentLocation", function(data) {
                            $scope.labels = $.parseJSON(data).stations;
                        });
                    };

                    $scope.getPriceCurrentLocation = function() {
                        $scope.msg = 'hallo';
                        $.post("services/rest/requestPriceCurrentLocation", function(data) {
                            names = data;
                            console.log(names);
                        });
                    };

                    $scope.openDialog = function(output) {
                        $scope.output = null;
                        $.post("services/rest/requestPriceCurrentLocation", function(data) {
                            $scope.output = data;
                        });
                        $mdDialog.show(
                                $mdDialog.alert()
                                        .clickOutsideToClose(true)
                                        .title('Billigste Tankstelle')
                                        .textContent($.parseJSON(output).stations[0].name)
                                        .ok('Schließen')
                        );
                    };

                    $scope.alertSlider = function() {
                        alert($scope.geoLocated.rad);
                    }
                });
    </script>
</head>
<body ng-app="selectStation" class="md-padding" ng-controller="selectStationContr" ng-cloak>
<h1 class="md-headline">Fuel Price Service - Find your gas station</h1>
<h1 class="md-subhead">Web-Service by Andreas Elsemann, Frederik Salzmann, Oliver Scholz</h1>
<md-content>
    <md-tabs class="md-accent" md-dynamic-height>

        <!-- tab for user input located request -->
        <md-tab id="userLocated">
            <md-tab-label>User Located</md-tab-label>
            <md-tab-body>
                <div class="form-in-tab">
                    <form name="userLocatedForm" action="services/rest/getPriceInCity" method="POST">
                        <div layout="row" class="md-padding">
                            <md-input-container class="md-padding">
                                <label>L&auml;ngengrad / Latitude</label>
                                <input required type="text" name="lat" ng-model="userLocated.lat" minlength="1" maxlength="12" />
                                <div ng-messages="userLocatedForm.userLocated.$error" role="alert">
                                    <div ng-message-exp="['required', 'minlength', 'maxlength']">
                                        Der L&auml;ngengrad muss eine Zahl sein! / The latitude has to be a number!
                                    </div>
                                </div>
                            </md-input-container>
                            <md-input-container class="md-padding">
                                <label>Breitengrad / Longitude</label>
                                <input required type="text" name="lon" ng-model="userLocated.lon" minlength="1" maxlength="12" />
                                <div ng-messages="userLocatedForm.userLocated.$error" role="alert">
                                    <div ng-message-exp="['required', 'minlength', 'maxlength']">
                                        Der Breitengrad muss eine Zahl sein! / The longitude has to be a number!
                                    </div>
                                </div>
                            </md-input-container>
                        </div>
                        <div layout="row" class="md-padding">
                            <md-input-container class="md-padding">
                                <label>Umkreis / Perimeter</label>
                                <input required type="number" name="rad" ng-model="userLocated.rad" minlength="1" maxlength="2" min="0" max="25" />
                                <div ng-messages="userLocated.userLocated.$error" role="alert" md-auto-hide="true">
                                    <div ng-message="['max', 'min', 'required']">
                                        Please insert a value between 0 and 25.
                                    </div>
                                </div>
                            </md-input-container>
                            <div flex="5" hide-xs hide-sm>
                                <!-- Spacer //-->
                            </div>
                            <md-input-container flex class="md-padding">
                                <label>Kraftstoffart / Sort of fuel</label>
                                <md-select ng-model="userLocated.sort">
                                    <md-option ng-repeat="sort in sorts" value="{{sort.name}}">{{sort.desc}}</md-option>
                                </md-select>
                            </md-input-container>
                        </div>
                        <md-button class="md-primary md-raised" type="submit">Send request</md-button>
                    </form>
                </div>
                <div class="description-in-tab">
                    <p>Description</p>
                    <p>
                        Enter (valid) coordinates in the input fields. The coordinates have to be located in Germany.
                        Example: Mosbach, Baden-W&uuml;rttemberg has the coordinates<br />
                        Latitude: 49.352<br />
                        Longitude: 9.146
                    </p>
                </div>
            </md-tab-body>
        </md-tab>

        <!-- TODO: POST request with Java Script, get form values by ng-model (see example md-slider, ng-model="geoLocated.rad" and function alertSlider() -->
        <!-- Tab for geo located request -->
        <md-tab id="geoLocated">
            <md-tab-label>Geo Located</md-tab-label>
            <md-tab-body>
                <div class="form-in-tab">
                    <div>
                        <form name="geoLocatedForm" action="services/rest/requestPriceCurrentLocation" method="POST">
                            <div layout="row" class="md-padding">
                                <span flex style="color: #e2001a">// Slider gibt kein value Attribut --> request hat keinen Wert für den Radius --> Error</span>
                                <md-slider-container flex class="md-padding" style="margin-top: 25px">
                                    <md-slider flex md-discrete class="md-primary" ng-model="geoLocated.rad" name="rad" value="{{rad}}" step="1" min="0" max="25"></md-slider>
                                </md-slider-container>
                                <md-input-container flex class="md-padding">
                                    <label>Kraftstoffart / Sort of fuel</label>
                                    <md-select ng-model="geoLocated.sort">
                                        <md-option ng-repeat="sort in sorts" value="{{sort.name}}">{{sort.desc}}</md-option>
                                    </md-select>
                                </md-input-container>
                            </div>
                            <md-button class="md-primary md-raised" ng-click="alertSlider()" type="submit">Send request</md-button>
                        </form>
                    </div>
                </div>
                <div class="description-in-tab">
                    <p>Description</p>
                    <p>When the user clicks the button, the RESTful service is invoked. This service requests the users current location by using the Google
                        Geolocation API. With the received data (latitude and longitude) the service requests the current fuel prices in the area.</p>
                </div>
            </md-tab-body>
        </md-tab>
    </md-tabs>
</md-content>



<br />
<br />
<br />




<div style="background-color: #eff0f1; padding: 20px">
    <b>Geo Located request. Result loaded in drop down list. Request is invoked when drop down list is opened.</b>
    <md-select ng-model="label" placeholder="Select fuel station" md-on-open="ngInit()" style="width: 500px">
        <md-option ng-value="label.name" ng-repeat="label in labels">Tankstelle: {{label.name}} // Preis: {{label.price}}</md-option>
    </md-select>
</div>
<form onclick="getPriceCurrentLocation()">
    <md-button class="md-primary md-raised">Send request</md-button>
</form>
<form>
    <md-button class="md-primary md-raised" onclick="openDialog()">Billigste Tankstelle (Dialog - funktioniert aber nicht)</md-button>
</form>

</body>
</html>