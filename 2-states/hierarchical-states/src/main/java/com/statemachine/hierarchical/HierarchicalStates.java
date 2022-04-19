package com.statemachine.hierarchical;

import com.statemachine.hierarchical.machine.Events;
import com.statemachine.hierarchical.machine.States;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineEventResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class HierarchicalStates implements CommandLineRunner {

	private final StateMachine<States, Events> stateMachine;

	public HierarchicalStates(StateMachine<States, Events> stateMachine) {
		this.stateMachine = stateMachine;
	}

	public static void main(String[] args) {
		SpringApplication.run(HierarchicalStates.class, args);
	}

	@Override
	public void run(String... args) {

		stateMachine.startReactively().subscribe();
		stateMachine.sendEvent(Events.E1);

//		Flux<StateMachineEventResult<States, Events>> result = stateMachine
//				.sendEvent(Mono.just(MessageBuilder.withPayload(Events.E2).build()));
//		result.subscribe();

	/*	stateMachine.sendEvent(Events.E3);
		stateMachine.sendEvent(Events.E4);
		stateMachine.sendEvent(Events.E5);
		stateMachine.sendEvent(Events.E6);*/


		//
	}
}
