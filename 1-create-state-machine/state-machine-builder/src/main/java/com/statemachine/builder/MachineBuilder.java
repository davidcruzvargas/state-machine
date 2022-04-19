package com.statemachine.builder;

import com.statemachine.builder.machine.Events;
import com.statemachine.builder.machine.States;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineEventResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class MachineBuilder implements CommandLineRunner {

	private final StateMachine<States, Events> stateMachine;

	public MachineBuilder(StateMachine<States, Events> stateMachine) {
		this.stateMachine = stateMachine;
	}

	public static void main(String[] args) {
		SpringApplication.run(MachineBuilder.class, args);
	}

	@Override
	public void run(String... args) {

		stateMachine.startReactively().subscribe();

		stateMachine.sendEvent(Events.E1);

		Flux<StateMachineEventResult<States, Events>> result = stateMachine
				.sendEvent(Mono.just(MessageBuilder.withPayload(Events.E2).build()));

		result.subscribe();
	}
}
