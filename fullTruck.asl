// Agent FullTruck in project Intelligent-Deporsitory.mas2j



/* Initial beliefs and rules */

at(gate) :- ~go_to_gate | go_to_selector | go_to_terminal.
at(selector_full) :- ~go_to_selector | go_to_terminal.
at(terminal) :- ~go_to_terminal.

/* Initial goals */
                                                                          
/* Plans */

+go_to_gate  <- .print("i am full and goint to the gate ");
					?pos(gate, X, Y);
				   !go(gate).
				 
+!go(A): true <- !at(A).

+!at(L) : at(L).

+!at(A) <- ?pos(A, X, Y);
			move(X, Y);
			!at(A).
			
			
+at_gate(G) <- .print("Im at gate");
				.send(G, tell, full).
				
				
+go_to_selector <- .print("i am going to selector");
					!go(selector_full).
					
+at_selector(S) <-   .print("reach selector ");
					.send(S, tell, go_to_t).
					
+go_to_terminal <- .print("i am empty and going to terminal");
					 !go(terminal).
			
			
