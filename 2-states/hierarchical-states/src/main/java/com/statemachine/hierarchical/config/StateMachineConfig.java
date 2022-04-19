package com.statemachine.hierarchical.config;

import com.statemachine.hierarchical.machine.Events;
import com.statemachine.hierarchical.machine.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Configuration
public class StateMachineConfig {

    @Bean
    StateMachine<States, Events> stateMachine() throws Exception {
        StateMachineBuilder.Builder<States, Events> builder = StateMachineBuilder.builder();

        builder.configureConfiguration()
                .withConfiguration()
                .listener(listener())
                .autoStartup(true);

        builder.configureStates()
                .withStates()
                .initial(States.Initial)
                .state(States.Initial)
                .state(States.ParentA)
                    .and()
                    .withStates()
                        .parent(States.ParentA)
                        .state(States.A1)
                        .initial(States.A1)
                        .state(States.A2)
                .and()
                .withStates()
                .state(States.ParentB)
                    .and()
                    .withStates()
                        .parent(States.ParentB)
                        .state(States.B1)
                        .initial(States.B1)
                        .state(States.B2);

        builder.configureTransitions()
                .withExternal()
                    .source(States.Initial)
                    .target(States.ParentA)
                    .event(Events.E1)
                .and()
                .withExternal()
                    .source(States.ParentA)
                    .target(States.A1)
                    .event(Events.E2)
                .and()
                .withExternal()
                    .source(States.A1)
                    .target(States.A2)
                    .event(Events.E3)
                .and()
                .withExternal()
                    .source(States.A2)
                    .target(States.ParentB)
                    .event(Events.E4)
                .and()
                .withExternal()
                    .source(States.ParentB)
                    .target(States.B1)
                    .event(Events.E5)
                .and()
                .withExternal()
                    .source(States.B1)
                    .target(States.B2)
                    .event(Events.E6);

        return builder.build();
    }

    @Bean
    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }
}
