class Solution {
    private static final int MAX = 100_001;

    public int lengthOfLIS(int[] nums, int k) {
        SegmentTree seg = new SegmentTree(MAX);
        int res = 0;

        for (int num : nums) {
            int left = Math.max(0, num - k);
            int right = num - 1;
            int maxInRange = seg.query(left, right);
            seg.update(num, maxInRange + 1);
            res = Math.max(res, maxInRange + 1);
        }

        return res;
    }

    static class SegmentTree {
        int[] tree;
        int size;

        SegmentTree(int size) {
            this.size = size;
            tree = new int[size * 4];
        }

        public void update(int index, int value) {
            update(1, 0, size - 1, index, value);
        }

        private void update(int node, int start, int end, int index, int value) {
            if (start == end) {
                tree[node] = Math.max(tree[node], value);
                return;
            }

            int mid = (start + end) / 2;
            if (index <= mid) {
                update(node * 2, start, mid, index, value);
            } else {
                update(node * 2 + 1, mid + 1, end, index, value);
            }

            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        public int query(int l, int r) {
            return query(1, 0, size - 1, l, r);
        }

        private int query(int node, int start, int end, int l, int r) {
            if (r < start || end < l) return 0;
            if (l <= start && end <= r) return tree[node];

            int mid = (start + end) / 2;
            return Math.max(
                query(node * 2, start, mid, l, r),
                query(node * 2 + 1, mid + 1, end, l, r)
            );
        }
    }
}
