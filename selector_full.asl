// Agent selector_full in project Intelligent-Deporsitory.mas2j



/* Initial beliefs and rules */



/* Initial goals */



!start.



/* Plans */



+!start : true <- .print("hello world.");
				+pos(terminal1, 4, 10);
				+pos(terminal2, 1, 9);
				+up.

+go_to_t[source(A)] <- print("there is a truck");
						!send_to_terminal(A).
						
						
+!send_to_terminal(A): open & up <- ?pos(terminal1, X, Y);
						.send(A, tell, pos(terminal, X, Y));
						.send(A, tell, go_to_terminal);
						-up;
						+down.
						
						
+!send_to_terminal(A): open & down <- ?pos(terminal2, X, Y);
						.send(A, tell, pos(terminal, X, Y));
						.send(A, tell, go_to_terminal);
						-down;
						+up.
					
						
+!send_to_terminal(A) <- ?pos(terminal2, X, Y);
						.send(A, tell, pos(terminal, X, Y));
						.send(A, tell, go_to_terminal).
