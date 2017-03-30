/*
 * Copyright 1997-2014 Optimatika (www.optimatika.se)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package biz.ojalgo.finance;

import java.math.BigDecimal;

import biz.ojalgo.BusinessObject;

/**
 * Lower &lt;= Target &lt;= Upper and Target ± Precision
 *
 * @author apete
 */
public interface Limit extends BusinessObject, LowerAndUpperLimit {

    abstract class Logic {

        public static BigDecimal getMaxAllowed(final Limit aLimit) {
            return aLimit.getTarget().add(aLimit.getPrecision());
        }

        public static BigDecimal getMinAllowed(final Limit aLimit) {
            return aLimit.getTarget().subtract(aLimit.getPrecision());
        }

        public static BigDecimal getTargetValue(final Limit aLimit) {
            return aLimit.getProfile().getAggregatedAmount().multiply(aLimit.getTarget());
        }

        public static boolean isWithinTargetPrecision(final Limit aLimit, final BigDecimal aValue) {
            return (aValue.compareTo(Logic.getMinAllowed(aLimit)) != -1) && (aValue.compareTo(Logic.getMaxAllowed(aLimit)) != 1);
        }

        public static String toDisplayString(final Limit aLimit) {
            return aLimit.getInstrumentCategory().getName() + "@" + aLimit.getProfile().getProfileGroup().getName();
        }

    }

    InstrumentCategory getInstrumentCategory();

    BigDecimal getPrecision();

    PortfolioProfile getProfile();

    BigDecimal getTarget();

    BigDecimal getTargetValue();

}
