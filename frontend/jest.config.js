export default {
  transform: {
    "^.+\\.js$": "babel-jest"
  },
  testEnvironment: "node",
  roots: ["<rootDir>/tests/"],
  moduleFileExtensions: ["js"],
};