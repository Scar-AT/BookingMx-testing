// Graph data structures and algorithms for nearby cities.
// All functions are kept pure for easier Jest unit testing.

/**
 * Represents an undirected graph where nodes are cities and
 * edges contain distances between them.
 */
export class Graph {
  constructor() {
    /** 
     * Adjacency list storing city connections.
     * @type {Map<string, Array<{to: string, distance: number}>>}
     */
    this.adj = new Map();
  }

  /**
   * Adds a new city to the graph if it does not already exist.
   *
   * @param {string} name - Name of the city to add.
   * @throws {Error} If the city name is invalid.
   */
  addCity(name) {
    if (!name || typeof name !== "string") {
      throw new Error("Invalid city name");
    }
    if (!this.adj.has(name)) {
      this.adj.set(name, []);
    }
  }

  /**
   * Adds an undirected edge between two cities.
   *
   * @param {string} from - Starting city.
   * @param {string} to - Destination city.
   * @param {number} distanceKm - Distance between the cities in kilometers.
   * @throws {Error} If either city does not exist or if the distance is invalid.
   */
  addEdge(from, to, distanceKm) {
    if (!this.adj.has(from) || !this.adj.has(to)) {
      throw new Error("Unknown city");
    }
    if (!Number.isFinite(distanceKm) || distanceKm < 0) {
      throw new Error("Invalid distance");
    }

    this.adj.get(from).push({ to, distance: distanceKm });
    this.adj.get(to).push({ to: from, distance: distanceKm }); // undirected
  }

  /**
   * Retrieves all neighbors of a given city.
   *
   * @param {string} city - City whose neighbors are required.
   * @returns {Array<{to: string, distance: number}>} Neighboring cities with distances.
   * @throws {Error} If the city is unknown.
   */
  neighbors(city) {
    if (!this.adj.has(city)) {
      throw new Error("Unknown city");
    }
    return [...this.adj.get(city)];
  }
}

/**
 * Validates a graph dataset consisting of a list of cities and edges.
 *
 * @param {{ cities: string[], edges: Array<{from: string, to: string, distance: number}> }} param0 
 *        Object containing cities and edges arrays.
 * @returns {{ ok: boolean, reason?: string }}
 *          Returns ok: true if valid, otherwise include a reason.
 */
export function validateGraphData({ cities, edges }) {
  if (!Array.isArray(cities) || !Array.isArray(edges)) {
    return { ok: false, reason: "cities/edges must be arrays" };
  }

  const citySet = new Set(cities);

  if (citySet.size !== cities.length) {
    return { ok: false, reason: "duplicate cities" };
  }

  for (const c of cities) {
    if (typeof c !== "string" || !c.trim()) {
      return { ok: false, reason: "invalid city entry" };
    }
  }

  for (const e of edges) {
    const { from, to, distance } = e ?? {};
    if (!citySet.has(from) || !citySet.has(to)) {
      return { ok: false, reason: "edge references unknown city" };
    }
    if (!Number.isFinite(distance) || distance < 0) {
      return { ok: false, reason: "invalid distance" };
    }
  }

  return { ok: true };
}

/**
 * Builds a new Graph instance using the provided cities and edges.
 *
 * @param {string[]} cities - List of city names.
 * @param {Array<{from: string, to: string, distance: number}>} edges - List of edges.
 * @returns {Graph} A fully constructed graph.
 */
export function buildGraph(cities, edges) {
  const g = new Graph();
  for (const c of cities) g.addCity(c);
  for (const { from, to, distance } of edges) g.addEdge(from, to, distance);
  return g;
}

/**
 * Finds nearby cities connected directly to a destination city, filtered by a maximum distance.
 *
 * @param {Graph} graph - The graph instance.
 * @param {string} destination - The city for which to find neighbors.
 * @param {number} [maxDistanceKm=250] - Maximum allowed distance.
 * @returns {Array<{city: string, distance: number}>} Sorted list of nearby cities.
 */
export function getNearbyCities(graph, destination, maxDistanceKm = 250) {
  if (!(graph instanceof Graph)) {
    throw new Error("graph must be Graph");
  }
  if (typeof destination !== "string" || !graph.adj.has(destination)) {
    return [];
  }

  const neighbors = graph.neighbors(destination);

  return neighbors
    .filter(n => n.distance <= maxDistanceKm)
    .sort((a, b) => a.distance - b.distance)
    .map(n => ({ city: n.to, distance: n.distance }));
}

/**
 * Sample dataset with example cities and edge distances.
 *
 * @type {{ cities: string[], edges: Array<{from: string, to: string, distance: number}> }}
 */
export const sampleData = {
  cities: [
    "Guadalajara", "Tlaquepaque", "Zapopan", "Tepatitlán",
    "Lagos de Moreno", "Tala", "Tequila"
  ],
  edges: [
    { from: "Guadalajara", to: "Zapopan", distance: 12 },
    { from: "Guadalajara", to: "Tlaquepaque", distance: 10 },
    { from: "Guadalajara", to: "Tepatitlán", distance: 78 },
    { from: "Guadalajara", to: "Tequila", distance: 60 },
    { from: "Zapopan", to: "Tala", distance: 35 },
    { from: "Tepatitlán", to: "Lagos de Moreno", distance: 85 }
  ]
};
