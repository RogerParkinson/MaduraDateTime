MaduraDateTime
==============

This provides an extensible framework for figuring holiday dates and similar anniversaries. Some of these dates may be mondayised, some not, and some, like Easter, are tricky to calculate.

The holidays are configured in an XML file that specifies which days are holidays. A number of generic definition classes are already included but you can add your own calculator for a specific holiday if you need to.

The existing definitions handle:

 * Absolute: A specific date. This is a one-off holiday that does not recur.
 * Anniversay: A specific date that recurs annually.
 * Mondayised: A specific date that recurs annually, shifted to the nearest Monday. There are switches to handle
		whether the following, previous or closest Monday is selected.
 * Weekend: Every Saturday and Sunday.
 * Weekday: A specific list of days of the week that are always holidays.
 * GoodFriday: Obvious.
 * EasterSunday: Obvious.
 * EasterMonday: Obvious.
 * ChineseNewYear: Obvious, though this is a crude approach that covers only 1995-2020. I hope to improve it.
 * Generic: Not used?

As the dates are calculated they are cached so that, especially the complex ones, do not need to be re-figured.