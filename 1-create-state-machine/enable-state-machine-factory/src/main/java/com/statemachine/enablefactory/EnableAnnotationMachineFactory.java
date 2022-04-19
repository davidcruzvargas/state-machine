package com.statemachine.enablefactory;

import com.statemachine.enablefactory.machine.Events;
import com.statemachine.enablefactory.machine.States;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

@SpringBootApplication
public class EnableAnnotationMachineFactory implements CommandLineRunner {


	private final StateMachineFactory<States, Events> factory;

	public EnableAnnotationMachineFactory(StateMachineFactory<States, Events> factory) {
		this.factory = factory;
	}

	public static void main(String[] args) {
		SpringApplication.run(EnableAnnotationMachineFactory.class, args);
	}

	@Override
	public void run(String... args) {
		StateMachine<States,Events> FirstStateMachine = factory.getStateMachine();
		FirstStateMachine.startReactively().subscribe();
		FirstStateMachine.sendEvent(Events.E1);
		FirstStateMachine.sendEvent(Events.E2);

		StateMachine<States,Events> SecondStateMachine = factory.getStateMachine();
		SecondStateMachine.startReactively().subscribe();
		SecondStateMachine.sendEvent(Events.E1);
	}
}
