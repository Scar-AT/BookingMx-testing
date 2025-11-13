import {
  Graph,
  validateGraphData,
  buildGraph,
  getNearbyCities,
  sampleData
} from "../js/graph.js";


// ------------------------------
// Graph Class Tests
// ------------------------------

describe("Graph Class", () => {
  test("addCity adds a new city", () => {
    const g = new Graph();
    g.addCity("A");
    expect(g.adj.has("A")).toBe(true);
  });

  test("addCity throws on invalid name", () => {
    const g = new Graph();
    expect(() => g.addCity("")).toThrow();
    expect(() => g.addCity(null)).toThrow();
    expect(() => g.addCity(123)).toThrow();
  });

  test("addEdge creates undirected connection", () => {
    const g = new Graph();
    g.addCity("A");
    g.addCity("B");
    g.addEdge("A", "B", 10);

    expect(g.neighbors("A")).toEqual([{ to: "B", distance: 10 }]);
    expect(g.neighbors("B")).toEqual([{ to: "A", distance: 10 }]);
  });

  test("addEdge throws if distance invalid", () => {
    const g = new Graph();
    g.addCity("A");
    g.addCity("B");

    expect(() => g.addEdge("A", "B", -1)).toThrow();
    expect(() => g.addEdge("A", "B", NaN)).toThrow();
    expect(() => g.addEdge("A", "B", Infinity)).toThrow();
  });

  test("addEdge throws if city does not exist", () => {
    const g = new Graph();
    g.addCity("A");

    expect(() => g.addEdge("A", "B", 5)).toThrow();
    expect(() => g.addEdge("Z", "A", 5)).toThrow();
  });

  test("neighbors returns the correct list", () => {
    const g = new Graph();
    g.addCity("A");
    g.addCity("B");
    g.addEdge("A", "B", 3);

    const list = g.neighbors("A");
    expect(list).toEqual([{ to: "B", distance: 3 }]);
  });

  test("neighbors throws if city unknown", () => {
    const g = new Graph();
    expect(() => g.neighbors("Missing")).toThrow();
  });
});

// ------------------------------
// validateGraphData Tests
// ------------------------------

describe("validateGraphData", () => {
  test("returns ok: true on valid dataset", () => {
    const result = validateGraphData({
      cities: ["A", "B"],
      edges: [{ from: "A", to: "B", distance: 5 }]
    });
    expect(result.ok).toBe(true);
  });

  test("fails if cities or edges are not arrays", () => {
    expect(validateGraphData({ cities: "x", edges: [] }).ok).toBe(false);
    expect(validateGraphData({ cities: [], edges: "x" }).ok).toBe(false);
  });

  test("fails on duplicate cities", () => {
    const res = validateGraphData({
      cities: ["A", "A"],
      edges: []
    });
    expect(res.ok).toBe(false);
  });

  test("fails on invalid city entries", () => {
    const res = validateGraphData({
      cities: ["", "A"],
      edges: []
    });
    expect(res.ok).toBe(false);
  });

  test("fails if edges reference unknown cities", () => {
    const res = validateGraphData({
      cities: ["A"],
      edges: [{ from: "A", to: "B", distance: 5 }]
    });
    expect(res.ok).toBe(false);
  });

  test("fails if edges contain invalid distance", () => {
    const res = validateGraphData({
      cities: ["A", "B"],
      edges: [{ from: "A", to: "B", distance: -1 }]
    });
    expect(res.ok).toBe(false);
  });
});

// ------------------------------
// buildGraph Tests
// ------------------------------

describe("buildGraph", () => {
  test("builds a graph with correct cities and edges", () => {
    const cities = ["A", "B"];
    const edges = [{ from: "A", to: "B", distance: 10 }];

    const g = buildGraph(cities, edges);
    expect(g).toBeInstanceOf(Graph);
    expect(g.neighbors("A")[0]).toEqual({ to: "B", distance: 10 });
  });
});

// ------------------------------
// getNearbyCities Tests
// ------------------------------

describe("getNearbyCities", () => {
  test("returns empty array for invalid graph", () => {
    expect(() => getNearbyCities({}, "A")).toThrow();
  });

  test("returns empty if destination invalid or unknown", () => {
    const g = new Graph();
    g.addCity("A");

    // invalid destination type
    expect(getNearbyCities(g, 123)).toEqual([]);

    // unknown destination
    expect(getNearbyCities(g, "Missing")).toEqual([]);
  });

  test("filters and sorts neighbors correctly", () => {
    const g = new Graph();
    g.addCity("A");
    g.addCity("B");
    g.addCity("C");
    g.addEdge("A", "B", 50);
    g.addEdge("A", "C", 10);

    const res = getNearbyCities(g, "A", 100);
    expect(res).toEqual([
      { city: "C", distance: 10 },
      { city: "B", distance: 50 }
    ]);
  });

  test("respects maxDistanceKm filtering", () => {
    const g = new Graph();
    g.addCity("A");
    g.addCity("B");
    g.addCity("C");
    g.addEdge("A", "B", 300);
    g.addEdge("A", "C", 50);

    const res = getNearbyCities(g, "A", 200);
    expect(res).toEqual([{ city: "C", distance: 50 }]);
  });
});


