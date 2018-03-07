'use strict';

describe('Controller Tests', function() {

    describe('HistoriaClinica Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockHistoriaClinica, MockPaciente, MockSintoma, MockMedico, MockInstitucion;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockHistoriaClinica = jasmine.createSpy('MockHistoriaClinica');
            MockPaciente = jasmine.createSpy('MockPaciente');
            MockSintoma = jasmine.createSpy('MockSintoma');
            MockMedico = jasmine.createSpy('MockMedico');
            MockInstitucion = jasmine.createSpy('MockInstitucion');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'HistoriaClinica': MockHistoriaClinica,
                'Paciente': MockPaciente,
                'Sintoma': MockSintoma,
                'Medico': MockMedico,
                'Institucion': MockInstitucion
            };
            createController = function() {
                $injector.get('$controller')("HistoriaClinicaDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'jappApp:historiaClinicaUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
