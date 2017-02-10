package dev.xesam.android.kit;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import dev.xesam.android.kit.util.CollectUtils;

/**
 * Created by xesamguo@gmail.com on 17-2-10.
 */

public class CollectUtilsTest {
    @Test
    public void testJoin1() {
        String res = CollectUtils.join(",", new Integer[]{1, 2, 3, 4});
        Assert.assertEquals("1,2,3,4", res);
    }

    @Test
    public void testJoin2() {
        String res = CollectUtils.join(",", new Integer[]{1});
        Assert.assertEquals("1", res);
    }

    @Test
    public void testJoin3() {
        String res = CollectUtils.join(",", new Integer[]{});
        Assert.assertEquals("", res);
    }

    @Test
    public void testJoin4() {
        Integer[] a = null;
        String res = CollectUtils.join(",", a);
        Assert.assertEquals("", res);
    }

    @Test
    public void testJoin5() {
        String res = CollectUtils.join(null, new Integer[]{1, 2, 3, 4});
        Assert.assertEquals("1234", res);
    }

    @Test
    public void testJoin6() {
        String res = CollectUtils.join(null, new Integer[]{1});
        Assert.assertEquals("1", res);
    }

    @Test
    public void testJoin7() {
        String res = CollectUtils.join(null, new Integer[]{});
        Assert.assertEquals("", res);
    }

    @Test
    public void testJoin8() {
        Integer[] a = null;
        String res = CollectUtils.join(null, a);
        Assert.assertEquals("", res);
    }

    @Test
    public void testJoin9() {
        String res = CollectUtils.join(",", new CollectUtils.Func1<Integer>() {
            @Override
            public String get(Integer raw) {
                return "a";
            }
        }, Arrays.asList(1, 2, 3, 4));
        Assert.assertEquals("a,a,a,a", res);
    }

    @Test
    public void testJoin10() {
        String res = CollectUtils.join(",", new CollectUtils.Func1<Integer>() {
            @Override
            public String get(Integer raw) {
                return "a";
            }
        }, null);
        Assert.assertEquals("", res);
    }
}
