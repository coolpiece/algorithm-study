package week11.p468380;

class Solution {
    int[] arr;
    long[] prefixLen;
    long[] prefixSum;

    public long[] solution(int[] arr, long l, long r) {
        this.arr = arr;

        int n = arr.length;
        prefixLen = new long[n];
        prefixSum = new long[n];

        for (int i = 0; i < n; i++) {
            prefixLen[i] = arr[i] + (i == 0 ? 0 : prefixLen[i - 1]);
            prefixSum[i] = (long) arr[i] * arr[i] + (i == 0 ? 0 : prefixSum[i - 1]);
        }

        long totalLen = prefixLen[n - 1];
        long size = r - l + 1;

        // 1. K 구하기
        long K = rangeSum(l, r);

        // 2. C 구하기
        long C = 0;
        long start = 1;
        long maxStart = totalLen - size + 1;

        long sum = rangeSum(1, size);

        int outIdx = 0;
        int inIdx = size + 1 <= totalLen ? findIndex(size + 1) : 0;

        while (start <= maxStart) {
            while (prefixLen[outIdx] < start) {
                outIdx++;
            }

            long outValue = arr[outIdx];

            long inPos = start + size;
            long inValue = 0;

            if (inPos <= totalLen) {
                while (prefixLen[inIdx] < inPos) {
                    inIdx++;
                }
                inValue = arr[inIdx];
            }

            long end = prefixLen[outIdx];

            if (inPos <= totalLen) {
                end = Math.min(end, prefixLen[inIdx] - size);
            }

            end = Math.min(end, maxStart);

            long sectionLength = end - start + 1;
            long diff = inValue - outValue;

            if (diff == 0) {
                if (sum == K) {
                    C += sectionLength;
                }
            } else {
                long target = K - sum;

                if (target % diff == 0) {
                    long move = target / diff;

                    if (0 <= move && move < sectionLength) {
                        C++;
                    }
                }
            }

            sum += sectionLength * diff;
            start = end + 1;
        }

        return new long[]{K, C};
    }

    // left ~ right 합
    private long rangeSum(long left, long right) {
        return prefix(right) - prefix(left - 1);
    }

    // 1번부터 pos번까지 합
    private long prefix(long pos) {
        if (pos <= 0) return 0;

        int idx = findIndex(pos);

        long beforeLen = idx == 0 ? 0 : prefixLen[idx - 1];
        long beforeSum = idx == 0 ? 0 : prefixSum[idx - 1];

        long count = pos - beforeLen;

        return beforeSum + count * arr[idx];
    }

    // pos번째 위치가 arr의 몇 번째 구간인지 찾기
    private int findIndex(long pos) {
        int left = 0;
        int right = prefixLen.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (prefixLen[mid] >= pos) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}