// Agent Selector in project Intelligent-Deporsitory.mas2j



/* Initial beliefs and rules */



/* Initial goals */



!start.



/* Plans */



+!start : true <- .print("hello world.");
				+pos(terminal3, 7, 10);
				+pos(terminal4, 10, 9);
				+up.
				
+at_selector(A) <-  .send(A, tell, at_selector);
					?pos(terminal3, X, Y);
					.send(A, tell, pos(terminal3, X, Y));
					.send(A, tell, go_to_terminal).
					
+go_to_t[source(A)] <- print("there is a truck");
						!send_to_terminal(A).
						
						
						
+!send_to_terminal(A): open & up <- ?pos(terminal3, X, Y);
						.send(A, tell, pos(terminal, X, Y));
						.send(A, tell, go_to_terminal);
						-up;
						+down.
						
						
+!send_to_terminal(A): open & down <- ?pos(terminal4, X, Y);
						.send(A, tell, pos(terminal, X, Y));
						.send(A, tell, go_to_terminal);
						-down;
						+up.
					
						
+!send_to_terminal(A) <- ?pos(terminal4, X, Y);
						.send(A, tell, pos(terminal, X, Y));
						.send(A, tell, go_to_terminal).

				  
