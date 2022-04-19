package com.statemachine.enable;

import com.statemachine.enable.machine.Events;
import com.statemachine.enable.machine.States;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class EnableAnnotationMachine implements CommandLineRunner {

	private final StateMachine<States, Events> stateMachine;

	public EnableAnnotationMachine(StateMachine<States, Events> stateMachine) {
		this.stateMachine = stateMachine;
	}

	public static void main(String[] args) {
		SpringApplication.run(EnableAnnotationMachine.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		stateMachine.sendEvent(Events.E1);
		stateMachine.sendEvent(Events.E2);
	}
}
