package guru.springframework.reactiveexamples;

import guru.springframework.reactiveexamples.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryImplTest {

    PersonRepositoryImpl personRepository;

    @BeforeEach
    void setUp() {
        personRepository = new PersonRepositoryImpl();
    }

    @Test
    void getById() {
        Mono<Person> personMono = personRepository.getById(1);
        Person person = personMono.block();
        System.out.println(person);
    }

    @Test
    void getByIdSubscribe() {
        Mono<Person> personMono = personRepository.getById(1);
        StepVerifier.create(personMono).expectNextCount(1).verifyComplete();
        personMono.subscribe(System.out::println);
    }

    @Test
    void getByIdMapFunction() {
        Mono<Person> personMono = personRepository.getById(1);
        personMono.map(Person::getFirstName).subscribe(System.out::println);
    }

    @Test
    void fluxTestBlockFirst() {
        Flux<Person> personFlux = personRepository.findAll();
        Person person = personFlux.blockFirst();
        System.out.println(person);
    }

    @Test
    void testFluxSubscribe() {
        Flux<Person> personFlux = personRepository.findAll();
        personFlux.subscribe(System.out::println);
    }

    @Test
    void testFluxToListMono() {
        Flux<Person> personFlux = personRepository.findAll();
        StepVerifier.create(personFlux).expectNextCount(4).verifyComplete();
        Mono<List<Person>> personListMono = personFlux.collectList();
        personListMono.subscribe(list -> {
            list.forEach(System.out::println);
        });
    }

    @Test
    void testFindPersonById() {
        Flux<Person> personFlux = personRepository.findAll();
        final Integer id = 3;
        Mono<Person> personMono = personFlux.filter(person -> Objects.equals(person.getId(), id)).next();
        personMono.subscribe(System.out::println);
    }

    @Test
    void testFindPersonByIdNotFound() {
        Flux<Person> personFlux = personRepository.findAll();
        final Integer id = 33;
        Mono<Person> personMono = personFlux.filter(person -> Objects.equals(person.getId(), id)).next();
        personMono.subscribe(System.out::println);
    }

    @Test
    void testFindPersonByIdNotFoundException() {
        Flux<Person> personFlux = personRepository.findAll();
        final Integer id = 33;
        Mono<Person> personMono = personFlux.filter(person -> Objects.equals(person.getId(), id)).single();
        personMono.doOnError(throwable -> {
            System.out.println("I went boom");
        }).onErrorReturn(Person.builder().build())
          .subscribe(System.out::println);
    }

    @Test
    void testFindByIdAssignment() {
        final Integer id = 12;
        Flux<Person> personFlux = personRepository.findAll();
        Mono<Person> monoPerson = personFlux.filter(person -> Objects.equals(person.getId(), id)).single();
        monoPerson.doOnError(throwable -> {
            System.out.println("Boom");
        }).onErrorReturn(Person.builder().build())
                .subscribe(System.out::println);
    }
}