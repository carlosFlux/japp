(function() {
    'use strict';

    angular
        .module('jappApp')
        .controller('HistoriaClinicaDetailController', HistoriaClinicaDetailController);

    HistoriaClinicaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'HistoriaClinica', 'Paciente', 'Sintoma', 'Medico', 'Institucion'];

    function HistoriaClinicaDetailController($scope, $rootScope, $stateParams, previousState, entity, HistoriaClinica, Paciente, Sintoma, Medico, Institucion) {
        var vm = this;

        vm.historiaClinica = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jappApp:historiaClinicaUpdate', function(event, result) {
            vm.historiaClinica = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
