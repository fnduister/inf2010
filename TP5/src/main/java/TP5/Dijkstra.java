package TP5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.sun.jdi.connect.VMStartException;

public class Dijkstra {

	private Graph graph;
	private Map<Node, Edge> dijkstraTable[];
	private Stack<Edge> path;

	public Dijkstra(Graph g) {
		this.graph = g;
	}

	public void findPath(Node s, Node d) {

		dijkstraTable = new HashMap[graph.getNodes().size()];
		Map<Node, Edge> finalMapDijkstra = new HashMap();

		path = new Stack<Edge>();

		// A complÃ©ter

		// initialiser la distance du noeud de départ a 0
		// ---------------------------------------------------------------------------
		Edge debut = new Edge(s, s, 0);
		HashMap<Node, Edge> map = new HashMap();
		map.putIfAbsent(s, debut);

		dijkstraTable[0] = map;
		List<Node> noeud_traite = new ArrayList<Node>();
		int distance_minimale = 0;
		Node noeud_courant = s;

		noeud_traite.add(0, noeud_courant);
		for (int i = 1; i < graph.getNodes().size(); i++) {

			// chercher les arcs connectés au noeud courant
			List<Edge> list_edge = graph.getEdgesGoingFrom(noeud_courant);
			// extraire les noeuds qui font office de destination a cet arc et n'ayant pas
			// ecorre ete traite
			List<Node> list_node = new ArrayList<Node>();
			boolean dejatraite;
			for (Edge edge : list_edge) {
				dejatraite = false;
				// vérifier si le noeud a déja été traité
				for (Node node : noeud_traite) {
					if (edge.getDestination() == node) {
						dejatraite = true;
					}
				}
				// ajouter le noeud s'il n'a pas encore ete traite
				if (dejatraite == false) {
					list_node.add(edge.getDestination());
				}
			}
			// calculer la distance qui sépare les noeuds du noeud courant

			HashMap<Node, Edge> mappe = new HashMap();
			for (Edge edge : list_edge) {
				dejatraite = false;
				// vérifier si le noeud a déja été traité
				for (Node node : noeud_traite) {
					if (edge.getDestination() == node) {
						dejatraite = true;
					}

				}
				if (dejatraite == false) {
					Edge arc = new Edge(noeud_courant, edge.getDestination(), distance_minimale + edge.getDistance());
					mappe.put(edge.getDestination(), arc);
				}

			}
			// ---------------------------------------------------------------------
			// ajouter les noeuds non traiter de l'ancienne itération qui n'ont pas ete
			// insérer dans l'itération présente

			for (Map.Entry<Node, Edge> entree : dijkstraTable[i - 1].entrySet()) {
				boolean condition = true;
				// verifier que le noeud appartient a l'ensemble des noeuds deja traite
				for (Node node : noeud_traite) {
					if (entree.getKey() == node) {
						condition = false;
						break;
					}
				}
				// verifier que le noeud appartient a l'ancienne iteratiion
				for (Map.Entry<Node, Edge> entry : mappe.entrySet()) {
					if (entree.getKey() == entry.getKey()) {
						condition = false;
						break;
					}
				}

				if (condition == true) {
					mappe.put(entree.getKey(), entree.getValue());
				}
			}
			// parcourir mappe

			// ajouter l'element x repectant les conditions
			// non traite et inexistant dans la mappe actuel

			// -----------------------------------------------------------------
			dijkstraTable[i] = mappe;
			// comparer les valeurs précedentes avec les valeurs actuelles et mettre a jour
			// la table

			for (int l = 0; l < graph.getNodes().size(); l++) {
				dejatraite = false;
				for (int compteur = 0; compteur < noeud_traite.size(); compteur++) {
					if (graph.getNodes().get(l) == noeud_traite.get(compteur)) {
						dejatraite = true;
						break;
					}
				}

				// vérifier que le noeud n'a pas encore ete traite
				if (dejatraite == false) {
					Edge edge_minimal = getMinimum(dijkstraTable[i].get(graph.getNodes().get(l)),
							dijkstraTable[i - 1].get(graph.getNodes().get(l)));
					// dijkstraTable[i].replace(list_node.get(l),edge_minimal);
					dijkstraTable[i].replace(graph.getNodes().get(l), edge_minimal);
				}
			}

			noeud_courant = getMinimum(dijkstraTable[i]);
			finalMapDijkstra.put(noeud_courant, dijkstraTable[i].get(noeud_courant));

			// chercher l'arc qui a pour destination le noeud courant dans la liste d'arcs
			Edge chercher = new Edge();

			for (Map.Entry<Node, Edge> entry : dijkstraTable[i].entrySet()) {

				if (entry.getKey() == noeud_courant) {
					chercher = entry.getValue();
					break;

				}
			}
			distance_minimale = chercher.getDistance();// la nouvelle distance est entre le noeud courant et le noeud de
														// depart
			noeud_traite.add(i, noeud_courant); // ajouter le noeud comme étant deja traité
		}

		// --------------------------------------------------------------------------------

		// trouver le chemin ...
		Edge previousEdge = finalMapDijkstra.get(d);
		path.add(finalMapDijkstra.get(d));
		while (previousEdge.getSource().getId() != s.getId()) {
			path.add(finalMapDijkstra.get(previousEdge.getSource()));
			previousEdge = finalMapDijkstra.get(previousEdge.getSource());
		}
	}

	private Node getMinimum(Map<Node, Edge> map) {
		Edge min = null;
		for (Node Key : map.keySet()) {
			if (min == null || map.get(Key).getDistance() < min.getDistance()) {
				min = map.get(Key);
			}
		}
		return min.getDestination();
	}

	private Edge getMinimum(Edge e1, Edge e2) {
		// A completer
		if (e1 == null && e2 != null)
			return e2;
		else if (e1 != null && e2 == null)
			return e1;
		else if (e1 == null && e2 == null)
			return null;
		else {
			Edge min = e2;
			if (e1.getDistance() < e2.getDistance()) {
				min = e1;
			}
			return min;
		}
	}

	public String afficherCourtChemin(Node source, Node destination) {
		// A completer
		String a = "le plus court chemin de " + source.getName() + " a " + destination.getName() + " est: "
				+ source.getName() + "  ";
		while (!path.empty()) {
			a += path.pop().getDestination().getName() + "  ";
		}
		return a;
	}

	public void afficherTable() {
		// A completer

		for (int i = 0; i < dijkstraTable.length; i++) {

			System.out.println("Itération" + (i + 1) + ":");
			for (Map.Entry<Node, Edge> entry : dijkstraTable[i].entrySet()) {

				System.out.println("Le noeud " + entry.getKey().getName() + " a pour distance "
						+ entry.getValue().getDistance() + " et pour source " + entry.getValue().getSource().getName());

			}

		}

	}
}
