package com.stgutah.playground.joda;

import net.objectlab.kit.datecalc.common.AbstractKitCalculatorsFactory;
import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.common.IMMDateCalculator;
import net.objectlab.kit.datecalc.common.PeriodCountCalculator;

/**
 * Local Date Kit Calculator Factory.
 *
 * User: dqromney
 * Date: Jan 31, 2011
 * Time: 2:08:32 PM
 */
public class LocalDateKitCalculatorsFactory extends AbstractKitCalculatorsFactory {

    /**
     * Create a new DateCalculator for a given name and type of handling.
     *
     * @param name               calendar name (holidays set interested in). If there is set of
     *                           holidays with that name, it will return a DateCalculator with
     *                           an empty holiday set (will work on Weekend only).
     * @param holidayHandlerType typically one of the value of HolidayHandlerType or null.
     * @return a new DateCalculator
     * @throws IllegalArgumentException if the type is not null or a valid value.
     */
    public DateCalculator getDateCalculator(String name, String holidayHandlerType) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Create a new PeriodCountCalculator.
     *
     * @return a PeriodCountCalculator
     */
    public PeriodCountCalculator getPeriodCountCalculator() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Create a new IMMDateCalculator.
     *
     * @return an IMMDateCalculator
     */
    public IMMDateCalculator getIMMDateCalculator() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
