package demoapp.dapulse.com.dapulsedemoapp.features.employees;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import demoapp.dapulse.com.dapulsedemoapp.dagger.ServerApi;
import demoapp.dapulse.com.dapulsedemoapp.features.employees.models.EmployeeResponse;
import rx.Observable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ofertour on 04/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeInteractorTest {

    @Mock
    ServerApi serverApi;

    @Mock
    EmployeesVIP.Repository repository;
    private EmployeeInteractor interactor;

    @Mock
    EmployeeResponse mockResponse;

    @Mock
    Observable<EmployeeResponse> mockObservable;

    @Before
    public void setup() {
        interactor = new EmployeeInteractor(serverApi, repository);
    }

    @Test
    public void when_db_is_empty_will_save_to_db() {
        when(repository.getResponse()).thenReturn(Observable.empty());
        when(serverApi.getEmployees()).thenReturn(Observable.just(mockResponse));

        interactor.loadEmployees().subscribe();

        verify(repository).saveData(mockResponse);
    }

    @Test
    public void when_db_is_not_empty_will_save_to_db() {
        when(repository.getResponse()).thenReturn(Observable.just(mockResponse));
        when(serverApi.getEmployees()).thenReturn(Observable.just(mockResponse));

        interactor.loadEmployees().subscribe();

        verify(repository, never()).saveData(mockResponse);
    }
}