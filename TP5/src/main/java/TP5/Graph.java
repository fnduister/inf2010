package TP5;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	private List<Node> nodes; // Noeuds
	private List<Edge> edges; // Les arcs

	public Graph() {
		// A compléter
		List<Node> nodes = new ArrayList<Node>();
		List<Edge> edges = new ArrayList<Edge>();
	}

	public List<Edge> getEdgesGoingFrom(Node source) {
		// A complèter
		List<Edge> listEdgeToReturn = new ArrayList<Edge>();

		for (Edge edge : edges) {
			if (edge.getSource() == source) {
				listEdgeToReturn.add(edge);
			}
		}

		return listEdgeToReturn;
	}

	public List<Edge> getEdgesGoingTo(Node dest) {
		// A complèter
		List<Edge> listEdgeToReturn = new ArrayList<Edge>();

		for (Edge edge : edges) {
			if (edge.getDestination() == dest) {
				listEdgeToReturn.add(edge);
			}
		}

		return listEdgeToReturn;
	}

	// Accesseurs
	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

}
