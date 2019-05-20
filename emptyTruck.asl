// Agent EmptyTruck in project Intelligent-Deporsitory.mas2j



/* Initial beliefs and rules */

at(gate) :- ~go_to_gate | go_to_selector | go_to_terminal.
at(selector) :- ~go_to_selector | go_to_terminal.
at(terminal) :- ~go_to_terminal.


/* Initial goals */


/* Plans */

+go_to_gate  <- .print("i am going to gate");
				 ?pos(gate, X, Y);
				 !go(gate).
				 				
				 
+!go(A): true <- !at(A).

+!at(A) : at(A).

+!at(A) <- ?pos(A, X, Y);
			move(X, Y);
			!at(A).
			
+at_gate(G) <- .print("Im at gate");
				.send(G, tell, empty).
			
+at_gate[source(S)] <- .print("im at gate").

+at_selector[source(s)] <- .print("i am at selector empty").

+at_selector(S) <-   .print("reach selector ");
					.send(S, tell, go_to_t).

+go_to_selector <- .print("i am going to selector");
					!go(selector).
					
+go_to_terminal <- .print("i am empty and going to terminal");
					 !go(terminal).
						



