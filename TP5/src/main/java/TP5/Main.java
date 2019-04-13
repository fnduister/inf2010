package TP5;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		Graph g = new Graph();
		
		// Partie 1: A completer : Création du graphe
		List<Node> listNodes = new ArrayList<Node>();
		Node A = new Node(0, "A");
		Node B = new Node(1, "B");
		Node C = new Node(2, "C");
		Node D = new Node(3, "D");
		Node E = new Node(4, "E");
		Node F = new Node(5, "F");
		Node G = new Node(6, "G");
		listNodes.add(A);
		listNodes.add(B);
		listNodes.add(C);
		listNodes.add(D);
		listNodes.add(E);
		listNodes.add(F);
		listNodes.add(G);

		List<Edge> listEdges = new ArrayList<Edge>();
		listEdges.add(new Edge(A, B, 2));
		listEdges.add(new Edge(A, C, 1));
		listEdges.add(new Edge(B, A, 2));
		listEdges.add(new Edge(B, D, 1));
		listEdges.add(new Edge(B, C, 2));
		listEdges.add(new Edge(B, E, 3));
		listEdges.add(new Edge(C, A, 1));
		listEdges.add(new Edge(C, B, 2));
		listEdges.add(new Edge(C, D, 4));
		listEdges.add(new Edge(C, E, 3));
		listEdges.add(new Edge(C, F, 5));
		listEdges.add(new Edge(D, B, 1));
		listEdges.add(new Edge(D, C, 4));
		listEdges.add(new Edge(D, F, 6));
		listEdges.add(new Edge(D, G, 5));
		listEdges.add(new Edge(E, B, 3));
		listEdges.add(new Edge(E, C, 3));
		listEdges.add(new Edge(F, C, 5));
		listEdges.add(new Edge(F, D, 6));
		listEdges.add(new Edge(F, E, 1));
		listEdges.add(new Edge(F, G, 2));
		listEdges.add(new Edge(G, D, 5));
		listEdges.add(new Edge(G, F, 2));
		
		g.setEdges(listEdges);
		g.setNodes(listNodes);
		// Partie 2: A completer : Implémentation de l’algorithme Dijkstra
		Dijkstra d = new Dijkstra(g);
		
		d.findPath(/* Spécifiez les paramètres */);
		
		d.afficherTable();

		// Partie 3 : Afficher le chemin le plus court
		System.out.println(d.afficherCourtChemin(/* Spécifiez les paramètres */));
	
	}
}
