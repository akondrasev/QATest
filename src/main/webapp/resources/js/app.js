var app = angular.module('myApp', ['ngRoute']);

// create services object using $http service
app.factory("services", ['$http', '$location', function ($http, $location) {
  var serviceBase = 'rest/';
  var obj = {};
  obj.getVms = function () {
    return handleHttpCall($http.get(serviceBase + 'vm'), $location);
  };

  obj.getVm = function (vmId) {
    return handleHttpCall($http.get(serviceBase + 'vm/' + vmId), $location);
  };

  obj.getVmState = function (vmId) {
    return handleHttpCall($http.get(serviceBase + 'vm/' + vmId + '/state'), $location);
  };

  obj.insertVm = function (vm) {
    return handleHttpCall($http.post(serviceBase + 'vm', vm), $location);
  };

  obj.updateVm = function (vmId, vm) {
    return handleHttpCall($http.put(serviceBase + 'vm/' + vmId, vm), $location);
  };

  obj.deleteVm = function (vmId) {
    return handleHttpCall($http.delete(serviceBase + 'vm/' + vmId), $location);
  };

  return obj;
}]);

// create controller for list.html
app.controller('ListController', function ($scope, services) {
  services.getVms().then(function (response) {
    if (response == null) {
      return;
    }
    $scope.vms = response.data;
  });
});

// create controller for edit_vm.html
app.controller('EditController', function ($scope, $rootScope, $location, $routeParams, services, vm) {
  if (vm == null) {
    return;
  }
  var vmId = ($routeParams.vmId) ? parseInt($routeParams.vmId) : 0;
  $rootScope.title = (vmId > 0) ? 'Edit virtual machine' : 'Add virtual machine';
  $scope.buttonText = (vmId > 0) ? 'Update virtual machine' : 'Add new virtual machine';
  $scope.vm = vm.data;

  $scope.deleteVm = function (vm) {
    if (confirm("Are you sure to delete virtual machine with id: " + $scope.vm.id) == true) {
      services.deleteVm(vm.id).then(function () {
        $location.path("/");
      });
    }
  };

  $scope.saveVm = function (vm) {
    var httpResponse = vmId > 0 ? services.updateVm(vmId, vm) : services.insertVm(vm);
    httpResponse.then(function () {
      $location.path("/");
    });
  };
});

// state controller for select in edit_vm.html
app.controller('StateController', function ($scope) {
  $scope.vmStates = [
    {value: 'POWERED_ON', label: 'Powered On'},
    {value: 'POWERED_OFF', label: 'Powered Off'},
    {value: 'SUSPENDED', label: 'Suspended'}
  ];
});

// set up API resource links in config module using $routeProvider provider
app.config(['$routeProvider',
  function ($routeProvider) {
    $routeProvider.when('/', {
          title: 'Virtual Machines',
          templateUrl: 'partials/list.html',
          controller: 'ListController'
        })
        .when('/edit_vm/:vmId', {
          title: 'Edit virtual machines',
          templateUrl: 'partials/edit_vm.html',
          controller: 'EditController',
          resolve: {
            vm: function (services, $route) {
              var vmId = $route.current.params.vmId;
              return services.getVm(vmId);
            }
          }
        })
        .otherwise({
          redirectTo: '/'
        });
  }]);

// set up run module to update title of the page
app.run(['$rootScope', function ($rootScope) {
  $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
    $rootScope.title = current.$$route.title;
  });
}]);