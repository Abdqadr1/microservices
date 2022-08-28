package com.qadr.reactiveweb;

import com.qadr.reactiveweb.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
class ReactivewebApplicationTests {

	private static class SleepClass {
		public static void sleep(Customer customer) {
			try{
				Thread.sleep(1000);
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
		}
	}

	@Test
	void traditionalWeb() {
		IntStream.range(1, 10)
				.mapToObj(i -> new Customer(i, "Customer "+i))
				.peek(SleepClass::sleep)
				.peek(System.out::println)
				.collect(Collectors.toList());

	}

	@Test
	void testMono(){
		Mono<?> mono_one = Mono.just("Mono one")
				.then(Mono.error( new Exception("Mono error occurred")))
				.log();
		mono_one.subscribe(System.out::println, e -> System.out.println(e.getMessage()));
	}

	@Test
	void testFlux(){
		Flux<String> stringFlux = Flux.just("1", "2").log();
		stringFlux.subscribe(System.out::println, e -> System.out.println(e.getMessage()));
	}

}
