package telran.performance;

public abstract class PerformanceTest {
	private String testName;
	int nRuns;

	public PerformanceTest(String testName, int nRuns) {
		this.testName = testName;
		this.nRuns = nRuns;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public int getnRuns() {
		return nRuns;
	}

	public void setnRuns(int nRuns) {
		this.nRuns = nRuns;
	}

	protected abstract void runTest();

	public void run() {
		long start = System.currentTimeMillis();

		for (int i = 0; i < nRuns; i++) {
			runTest();
		}

		long finishTime = System.currentTimeMillis();

		dispalyResult(start, finishTime);
	}

	private void dispalyResult(long start, long finishTime) {

		System.out.printf("Test : %s -> " + " Number of runs: %d;" + " Running time : %d;\n", testName, nRuns,
				finishTime - start);

	}

}
