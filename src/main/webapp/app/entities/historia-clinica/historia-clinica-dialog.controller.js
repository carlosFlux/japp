(function() {
    'use strict';

    angular
        .module('jappApp')
        .controller('HistoriaClinicaDialogController', HistoriaClinicaDialogController);

    HistoriaClinicaDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'HistoriaClinica', 'Paciente', 'Sintoma', 'Medico', 'Institucion'];

    function HistoriaClinicaDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, HistoriaClinica, Paciente, Sintoma, Medico, Institucion) {
        var vm = this;

        vm.historiaClinica = entity;
        vm.clear = clear;
        vm.save = save;
        vm.pacientes = Paciente.query();
        vm.sintomas = Sintoma.query({filter: 'historiaclinica-is-null'});
        $q.all([vm.historiaClinica.$promise, vm.sintomas.$promise]).then(function() {
            if (!vm.historiaClinica.sintomaId) {
                return $q.reject();
            }
            return Sintoma.get({id : vm.historiaClinica.sintomaId}).$promise;
        }).then(function(sintoma) {
            vm.sintomas.push(sintoma);
        });
        vm.medicos = Medico.query();
        vm.institucions = Institucion.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.historiaClinica.id !== null) {
                HistoriaClinica.update(vm.historiaClinica, onSaveSuccess, onSaveError);
            } else {
                HistoriaClinica.save(vm.historiaClinica, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jappApp:historiaClinicaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
