package ir.tdaapp.paymanyar.Model.Utilitys;

import com.digidemic.unitof.UnitOf;

import java.util.ArrayList;

public class UnitConversion {

    public ArrayList<String> ShowSpinnerValues(int mode){
        ArrayList<String> answer=new ArrayList<>();

        switch (mode){

            // Acceleration
            case 1:
//                answer.add("AttometersPerSecondSquared");
                answer.add("CentimeterPerSecondSquared");
                answer.add("DecimetersPerSecondSquared");
                answer.add("DekametersPerSecondSquared");
                answer.add("ExametersPerSecondSquared");
                answer.add("FeetPerHourPerSecond");
                answer.add("FeetPerMinutePerSecond");
                answer.add("FeetPerSecondSquared");
                answer.add("FemtometersPerSecondSquared");
                answer.add("Galileos");
                answer.add("GigametersPerSecondSquared");
                answer.add("Gravity");
                answer.add("HectometersPerSecondSquared");
                answer.add("InchesPerMinutePerSecond");
                answer.add("InchesPerSecondSquared");
                answer.add("KilometersPerHourPerSecond");
                answer.add("KilometersPerHourSquared");
                answer.add("KilometersPerSecondSquared");
                answer.add("KnotsPerSecond");
                answer.add("MegametersPerSecondSquared");
                answer.add("MetersPerSecondSquared");
                answer.add("Microgalileos");
                answer.add("MicrometersPerSecondSquared");
                answer.add("MilesPerHourPerSecond");
                answer.add("MilesPerMinutePerSecond");
                answer.add("MilesPerSecondSquared");
                answer.add("Milligalileos");
                answer.add("MillimetersPerSecondSquared");
                answer.add("NanometersPerSecondSquared");
                answer.add("PetametersPerSecondSquared");
                answer.add("PicometersPerSecondSquared");
                answer.add("TerametersPerSecondSquared");
                answer.add("YardsPerSecondSquared");
                break;

            // Angel
            case 2:
                answer.add("Degrees");
                answer.add("Gradians");
                answer.add("Mils");
                answer.add("Minutes");
                answer.add("Quadrants");
                answer.add("Radians");
                answer.add("Revolutions");
                answer.add("RightAngles");
                answer.add("Seconds");
                answer.add("Sextants");
                answer.add("Signs");
                answer.add("Turns");
                break;

            // Area
            case 3:
                answer.add("Are");
                answer.add("Acre");
//                answer.add("Barns");
//                answer.add("CircularInches");
//                answer.add("CircularMils");
                answer.add("Hectare");
//                answer.add("Homesteads");
//                answer.add("Roods");
//                answer.add("Sabins");
                answer.add("cm2");
//                answer.add("SquareChains");
//                answer.add("SquareDecimeters");
//                answer.add("SquareDekameters");
                answer.add("ft2");
//                answer.add("SquareHectometers");
                answer.add("in2");
                answer.add("km2");
                answer.add("m2");
//                answer.add("SquareMicrometers");
                answer.add("mile2");
                answer.add("mm2");
//                answer.add("SquareNanometers");
//                answer.add("SquarePerches");
//                answer.add("SquarePoles");
//                answer.add("SquareRods");
                answer.add("yare2");
                break;

            // Electric Charge
            case 4:
                answer.add("Abcoulombs");
                answer.add("AmpereHours");
                answer.add("AmpereMinutes");
                answer.add("AmpereSeconds");
                answer.add("Coulombs");
                answer.add("EMUsOfCharge");
                answer.add("ESUsOfCharge");
                answer.add("ElectronCharge");
                answer.add("FaradVolts");
                answer.add("FaradayCarbon12");
                answer.add("FaradayChemistry");
                answer.add("FaradayPhysics");
                answer.add("Franklins");
                answer.add("Kilocoulombs");
                answer.add("Megacoulombs");
                answer.add("Microcoulombs");
                answer.add("Millicoulombs");
                answer.add("Nanocoulombs");
                answer.add("Picocoulombs");
                answer.add("Statcoulombs");
                break;

            // Distance
            case 5:
//                answer.add("Attometers");
//                answer.add("Barleycorns");
//                answer.add("CablesImperial");
//                answer.add("CablesInternational");
//                answer.add("CablesUSCustomary");
//                answer.add("Caliber");
//                answer.add("Centiinches");
                answer.add("cm");
//                answer.add("Chains");
//                answer.add("Cubits");
//                answer.add("Decimeters");
//                answer.add("Dekameters");
//                answer.add("Ells");
//                answer.add("Exameters");
//                answer.add("Fathoms");
                answer.add("ft");
//                answer.add("Femtometers");
//                answer.add("Fingers");
//                answer.add("Furlongs");
//                answer.add("Gigameters");
//                answer.add("Hands");
//                answer.add("Hectometers");
                answer.add("in");
                answer.add("km");
//                answer.add("Kiloyards");
//                answer.add("Leagues");
//                answer.add("LightYears");
//                answer.add("Links");
//                answer.add("Megameters");
                answer.add("m");
//                answer.add("Microinches");
//                answer.add("Micrometers");
                answer.add("Microns");
                answer.add("Miles");
                answer.add("mm");
//                answer.add("Nails");
//                answer.add("Nanometers");
//                answer.add("NauticalLeaguesInternational");
//                answer.add("NauticalLeaguesUK");
//                answer.add("NauticalMilesInternational");
//                answer.add("NauticalMilesUK");
//                answer.add("NauticalMilesUSCustomary");
//                answer.add("Perches");
//                answer.add("Petameters");
//                answer.add("Picometers");
//                answer.add("Poles");
//                answer.add("Rods");
//                answer.add("Ropes");
//                answer.add("Spans");
//                answer.add("Terameters");
//                answer.add("ThousandthInches");
                answer.add("Yards");
                break;

            // Energy
            case 6:
                answer.add("BTUsInternationalStandard");
                answer.add("BTUsThermochemical");
                answer.add("BarrelsOfOilEquivalent");
                answer.add("CaloriesInternationalSteam");
                answer.add("CaloriesNutritional");
                answer.add("CaloriesThermochemical");
                answer.add("DyneCentimeters");
                answer.add("ElectronVolts");
                answer.add("Ergs");
                answer.add("FootPounds");
                answer.add("Gigajoules");
                answer.add("GigatonsOfTNT");
                answer.add("GigawattHours");
                answer.add("GramForceCentimeters");
                answer.add("GramForceMeters");
                answer.add("Hartrees");
                answer.add("HorsepowerHours");
                answer.add("HorsepowerHoursMetric");
                answer.add("InchOunces");
                answer.add("InchPounds");
                answer.add("Joules");
                answer.add("KilocaloriesInternationalSteam");
                answer.add("KilocaloriesThermochemical");
                answer.add("KiloelectronVolts");
                answer.add("KilogramForceCentimeters");
                answer.add("KilogramForceMeters");
                answer.add("KilogramsOfTNT");
                answer.add("Kilojoules");
                answer.add("KilopondMeters");
                answer.add("KilotonsOfTNT");
                answer.add("KilowattHours");
                answer.add("KilowattSeconds");
                answer.add("LiterAtmospheres");
                answer.add("MegaBTUsInternationalStandard");
                answer.add("MegaelectronVolts");
                answer.add("Megajoules");
                answer.add("MegatonsOfTNT");
                answer.add("MegawattHours");
                answer.add("Microjoules");
                answer.add("Millijoules");
                answer.add("Nanojoules");
                answer.add("NewtonMeters");
                answer.add("PlanckEnergy");
                answer.add("PoundalFeet");
                answer.add("Rydbergs");
                answer.add("ThermsEC");
                answer.add("ThermsUS");
                answer.add("TonsOfTNT");
                answer.add("WattHours");
                answer.add("WattSeconds");
                break;

            // Ferequence
            case 7:
                answer.add("Attohertz");
                answer.add("Centihertz");
                answer.add("CyclesPerSecond");
                answer.add("Decihertz");
                answer.add("Dekahertz");
                answer.add("Exahertz");
                answer.add("Femtohertz");
                answer.add("Gigahertz");
                answer.add("Hectohertz");
                answer.add("Hertz");
                answer.add("Kilohertz");
                answer.add("Megahertz");
                answer.add("Microhertz");
                answer.add("Millihertz");
                answer.add("Nanohertz");
                answer.add("Petahertz");
                answer.add("Picohertz");
                answer.add("RevolutionsPerDay");
                answer.add("RevolutionsPerHour");
                answer.add("RevolutionsPerMinute");
                answer.add("RevolutionsPerSecond");
                answer.add("Terahertz");
                break;

            // Force
            case 8:
                answer.add("Attonewtons");
                answer.add("Centinewtons");
                answer.add("Decinewtons");
                answer.add("Dekanewtons");
                answer.add("Dynes");
                answer.add("Exanewtons");
                answer.add("Femtonewtons");
                answer.add("Giganewtons");
                answer.add("GramForces");
                answer.add("GraveForces");
                answer.add("Hectonewtons");
                answer.add("JouleCentimeters");
                answer.add("JouleMeters");
                answer.add("KilogramForces");
                answer.add("Kilonewtons");
                answer.add("Kiloponds");
                answer.add("KilopoundForces");
                answer.add("LongTonForces");
                answer.add("Meganewtons");
                answer.add("MetricTonForces");
                answer.add("Micronewtons");
                answer.add("MilligraveForces");
                answer.add("Millinewtons");
                answer.add("Nanonewtons");
                answer.add("Newtons");
                answer.add("OunceForces");
                answer.add("Petanewtons");
                answer.add("Piconewtons");
                answer.add("Ponds");
                answer.add("PoundFeetPerSecondSquared");
                answer.add("PoundForces");
                answer.add("Poundals");
                answer.add("ShortTonForces");
                answer.add("Sthenes");
                answer.add("Teranewtons");
                break;

            // Temperature
            case 9:
                answer.add("Celsius");
                answer.add("Fahrenheit");
                answer.add("Kelvin");
                answer.add("Rankine");
                answer.add("Reaumur");
                break;

            // Mass
            case 10:
                answer.add("Carats");
                answer.add("Centigrams");
                answer.add("Centners");
                answer.add("Decigrams");
                answer.add("Dekagrams");
                answer.add("Exagrams");
                answer.add("Femtograms");
                answer.add("Gamma");
                answer.add("Gigagrams");
                answer.add("Grams");
                answer.add("Hectograms");
                answer.add("Kilograms");
                answer.add("Kilopounds");
                answer.add("KilotonsMetric");
                answer.add("Megagrams");
                answer.add("Micrograms");
                answer.add("Milligrams");
                answer.add("Nanograms");
                answer.add("OuncesMetric");
                answer.add("OuncesUS");
                answer.add("Petagrams");
                answer.add("Picograms");
                answer.add("Pounds");
                answer.add("Quintals");
                answer.add("Slugs");
                answer.add("StonesUK");
                answer.add("StonesUS");
                answer.add("Teragrams");
                answer.add("TonsImperial");
                answer.add("TonsMetric");
                answer.add("TonsUS");
                break;

            // Numeric
            case 11:
                answer.add("Binary");
                answer.add("Decimal");
                answer.add("Hexadecimal");
                answer.add("Octal");
                break;

            // Pressure
            case 12:
                answer.add("Bars");
                answer.add("Baryes");
                answer.add("CentimetersOfMercury0C");
                answer.add("CentimetersOfWater4C");
                answer.add("Centipascals");
                answer.add("Decibars");
                answer.add("Decipascals");
                answer.add("Dekapascals");
                answer.add("DynesPerSquareCentimeter");
                answer.add("Exapascals");
                answer.add("FeetOfSeaWater");
                answer.add("FeetOfWater4C");
                answer.add("FeetOfWater60F");
                answer.add("Femtopascals");
                answer.add("Gigapascals");
                answer.add("GramsPerSquareCentimeter");
                answer.add("Hectopascals");
                answer.add("InchesOfMercury32F");
                answer.add("InchesOfMercury60F");
                answer.add("InchesOfWater4C");
                answer.add("InchesOfWater60F");
                answer.add("KSI");
                answer.add("KilogramsPerSquareCentimeter");
                answer.add("KilogramsPerSquareMeter");
                answer.add("KilogramsPerSquareMillimeter");
                answer.add("KilonewtonsPerSquareMeter");
                answer.add("Kilopascals");
                answer.add("KipsPerSquareInch");
                answer.add("LongTonsPerSquareFoot");
                answer.add("LongTonsPerSquareInch");
                answer.add("Megapascals");
                answer.add("MetersOfSeaWater");
                answer.add("MetersOfWater4C");
                answer.add("Microbars");
                answer.add("Micropascals");
                answer.add("Millibars");
                answer.add("MillimetersOfMercury0C");
                answer.add("MillimetersOfWater4C");
                answer.add("Millipascals");
                answer.add("Nanopascals");
                answer.add("NewtonsPerSquareCentimeter");
                answer.add("NewtonsPerSquareMeter");
                answer.add("NewtonsPerSquareMillimeter");
                answer.add("PSI");
                answer.add("Pascals");
                answer.add("Petapascals");
                answer.add("Picopascals");
                answer.add("Pieze");
                answer.add("PoundalsPerSquareFoot");
                answer.add("PoundsPerSquareFoot");
                answer.add("PoundsPerSquareInch");
                answer.add("ShortTonsPerSquareFoot");
                answer.add("ShortTonsPerSquareInch");
                answer.add("StandardAtmospheres");
                answer.add("SthenesPerSquareMeter");
                answer.add("TechnicalAtmospheres");
                answer.add("Terapascals");
                answer.add("Torrs");
                break;

            // Speed
            case 13:
                answer.add("CentimetersPerHour");
                answer.add("CentimetersPerMinute");
                answer.add("CentimetersPerSecond");
                answer.add("EarthsVelocity");
                answer.add("FeetPerHour");
                answer.add("FeetPerMinute");
                answer.add("FeetPerSecond");
                answer.add("FirstCosmicVelocity");
                answer.add("InchesPerHour");
                answer.add("InchesPerMinute");
                answer.add("InchesPerSecond");
                answer.add("KilometersPerHour");
                answer.add("KilometersPerMinute");
                answer.add("KilometersPerSecond");
                answer.add("Knots");
                answer.add("Light");
                answer.add("Mach");
                answer.add("MetersPerHour");
                answer.add("MetersPerMinute");
                answer.add("MetersPerSecond");
                answer.add("MilesPerHour");
                answer.add("MilesPerMinute");
                answer.add("MilesPerSecond");
                answer.add("MillimetersPerHour");
                answer.add("MillimetersPerMinute");
                answer.add("MillimetersPerSecond");
                answer.add("SecondCosmicVelocity");
                answer.add("SoundsInAir");
                answer.add("SoundsInWater");
                answer.add("ThirdCosmicVelocity");
                answer.add("YardsPerHour");
                answer.add("YardsPerMinute");
                answer.add("YardsPerSecond");
                break;

            // Time
            case 14:
                answer.add("Attoseconds");
                answer.add("Centuries");
                answer.add("Days");
                answer.add("Decades");
                answer.add("Femtoseconds");
                answer.add("Fortnights");
                answer.add("GregorianYears");
                answer.add("Hours");
                answer.add("JulianYears");
                answer.add("LeapYears");
                answer.add("Microseconds");
                answer.add("Millenniums");
                answer.add("Milliseconds");
                answer.add("Minutes");
                answer.add("Months");
                answer.add("Nanoseconds");
                answer.add("Picoseconds");
                answer.add("Seconds");
                answer.add("Weeks");
                answer.add("Years");
                break;

            // Torque
            case 15:
                answer.add("DyneCentimeters");
                answer.add("DyneMeters");
                answer.add("DyneMillimeters");
                answer.add("GramCentimeters");
                answer.add("GramMeters");
                answer.add("GramMillimeters");
                answer.add("KilogramCentimeters");
                answer.add("KilogramMeters");
                answer.add("KilogramMillimeters");
                answer.add("KilonewtonMeters");
                answer.add("NewtonCentimeters");
                answer.add("NewtonMeters");
                answer.add("NewtonMillimeters");
                answer.add("OunceFeet");
                answer.add("OunceInches");
                answer.add("PoundFeet");
                answer.add("PoundInches");
                break;

            // Volume
            case 16:

//                answer.add("AcreFeetUSSurvey");
//                answer.add("AcreInches");
//                answer.add("ArceFeet");
//                answer.add("Attoliters");
//                answer.add("BarrelsOfOil");
                answer.add("bbl");
//                answer.add("BarrelsUS");
//                answer.add("BoardFeet");
//                answer.add("Centiliters");
//                answer.add("Cords");
                answer.add("cm3");
//                answer.add("CubicDecimeters");
                answer.add("ft3");
                answer.add("in3");
//                answer.add("CubicKilometers");
                answer.add("m3");
//                answer.add("CubicMiles");
//                answer.add("CubicMillimeters");
                answer.add("yard3");
//                answer.add("CupsMetric");
//                answer.add("CupsUK");
//                answer.add("CupsUS");
//                answer.add("Deciliters");
//                answer.add("Decisteres");
//                answer.add("Dekaliters");
//                answer.add("Dekasteres");
//                answer.add("DessertspoonsUK");
//                answer.add("DessertspoonsUS");
//                answer.add("Drops");
//                answer.add("Exaliters");
//                answer.add("Femtoliters");
//                answer.add("FluidOuncesUK");
                answer.add("fl.oz");
                answer.add("gal IM");
                answer.add("gal US");
//                answer.add("Gigaliters");
//                answer.add("GillsUK");
//                answer.add("GillsUS");
//                answer.add("Hectoliters");
//                answer.add("Hogsheads");
//                answer.add("HundredCubicFeet");
//                answer.add("Kiloliters");
                answer.add("lit");
//                answer.add("Megaliters");
//                answer.add("Microliters");
                answer.add("ml");
//                answer.add("MinimsUK");
//                answer.add("MinimsUS");
//                answer.add("Nanoliters");
//                answer.add("Petaliters");
//                answer.add("Picoliters");
//                answer.add("PintsUK");
//                answer.add("PintsUS");
//                answer.add("QuartsUK");
//                answer.add("QuartsUS");
//                answer.add("RegisterTons");
//                answer.add("Steres");
//                answer.add("TablespoonsMetric");
//                answer.add("TablespoonsUK");
//                answer.add("TablespoonsUS");
//                answer.add("TeaspoonsMetric");
//                answer.add("TeaspoonsUK");
//                answer.add("TeaspoonsUS");
//                answer.add("Teraliters");
//                answer.add("Tuns");

                break;


        }

        return answer;
    }

    public String ConvertValues(String value,int mode,String value_unit,String answer_unit){
        String ans="0";

        switch (mode){

            // Acceleration
            case 1:
                ans=Acceleration(value,value_unit,answer_unit);
                break;

            // Angel
            case 2:
                ans=Angel(value,value_unit,answer_unit);
                break;

            // Area
            case 3:
                ans=Area(value,value_unit,answer_unit);
                break;

            // Electric Type
            case 4:
                ans=Electric(value,value_unit,answer_unit);
                break;

            // Distance
            case 5:
                ans=Distance(value,value_unit,answer_unit);
                break;

            // Energy
            case 6:
                ans=Enegy(value,value_unit,answer_unit);
                break;

            // Ferequence
            case 7:
                ans=Ferequence(value,value_unit,answer_unit);
                break;

            // Force
            case 8:
                ans=Force(value,value_unit,answer_unit);
                break;

            // Tempareture
            case 9:
                ans=Temperature(value,value_unit,answer_unit);
                break;

            // Mass
            case 10:
                ans=Mass(value,value_unit,answer_unit);
                break;

            // Numeric
            case 11:
                ans=Numeric(value,value_unit,answer_unit);
                break;

            // Pressure
            case 12:
                ans=Pressure(value,value_unit,answer_unit);
                break;

            // Speed
            case 13:
                ans=Speed(value,value_unit,answer_unit);
                break;

            // Time
            case 14:
                ans=Time(value,value_unit,answer_unit);
                break;

            // Torque
            case 15:
                ans=Torque(value,value_unit,answer_unit);
                break;

            // Volume
            case 16:
                ans=Volume(value,value_unit,answer_unit);
                break;


        }


        return ans;
    }

    private String Acceleration(String value,String value_unit,String answer_unit){
        String ans="0";
        UnitOf.Acceleration unitOf;
        switch (value_unit){

            case "AttometersPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromAttometersPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "CentimeterPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromCentimeterPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "DecimetersPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromDecimetersPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "DekametersPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromDekametersPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "ExametersPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromExametersPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "FeetPerHourPerSecond":
                unitOf=new UnitOf.Acceleration().fromFeetPerHourPerSecond(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "FeetPerMinutePerSecond":
                unitOf=new UnitOf.Acceleration().fromFeetPerMinutePerSecond(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "FeetPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromFeetPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "FemtometersPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromFemtometersPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "Galileos":
                unitOf=new UnitOf.Acceleration().fromGalileos(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "GigametersPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromGigametersPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "Gravity":
                unitOf=new UnitOf.Acceleration().fromGravity(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "HectometersPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromHectometersPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "InchesPerMinutePerSecond":
                unitOf=new UnitOf.Acceleration().fromInchesPerMinutePerSecond(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "InchesPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromInchesPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "KilometersPerHourPerSecond":
                unitOf=new UnitOf.Acceleration().fromKilometersPerHourPerSecond(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;

            case "KilometersPerHourSquared":
                unitOf=new UnitOf.Acceleration().fromKilometersPerHourSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "KilometersPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromKilometersPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "KnotsPerSecond":
                unitOf=new UnitOf.Acceleration().fromKnotsPerSecond(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "MegametersPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromMegametersPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "MetersPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromMetersPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "Microgalileos":
                unitOf=new UnitOf.Acceleration().fromMicrogalileos(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "MicrometersPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromMicrometersPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "MilesPerHourPerSecond":
                unitOf=new UnitOf.Acceleration().fromMilesPerHourPerSecond(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "MilesPerMinutePerSecond":
                unitOf=new UnitOf.Acceleration().fromMilesPerMinutePerSecond(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "MilesPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromMilesPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "Milligalileos":
                unitOf=new UnitOf.Acceleration().fromMilligalileos(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "MillimetersPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromMillimetersPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "NanometersPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromNanometersPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "PetametersPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromPetametersPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "PicometersPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromPicometersPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "TerametersPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromTerametersPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
            case "YardsPerSecondSquared":
                unitOf=new UnitOf.Acceleration().fromYardsPerSecondSquared(Double.valueOf(value));
                ans=AccelerationHelper(answer_unit,unitOf);
                break;
        }
        return ans;
    }

    private String AccelerationHelper(String answer_unit,UnitOf.Acceleration unitOf){
        String ans="0";
        switch (answer_unit){

            case "AttometersPerSecondSquared":
                ans=String.valueOf(unitOf.toAttometersPerSecondSquared());
                break;

            case "CentimeterPerSecondSquared":
                ans=String.valueOf(unitOf.toCentimeterPerSecondSquared());
                break;
            case "DecimetersPerSecondSquared":
                ans=String.valueOf(unitOf.toDecimetersPerSecondSquared());
                break;
            case "DekametersPerSecondSquared":
                ans=String.valueOf(unitOf.toDekametersPerSecondSquared());
                break;
            case "ExametersPerSecondSquared":
                ans=String.valueOf(unitOf.toExametersPerSecondSquared());
                break;
            case "FeetPerHourPerSecond":
                ans=String.valueOf(unitOf.toFeetPerHourPerSecond());
                break;
            case "FeetPerMinutePerSecond":
                ans=String.valueOf(unitOf.toFeetPerMinutePerSecond());
                break;
            case "FeetPerSecondSquared":
                ans=String.valueOf(unitOf.toFeetPerSecondSquared());
                break;
            case "FemtometersPerSecondSquared":
                ans=String.valueOf(unitOf.toFemtometersPerSecondSquared());
                break;
            case "Galileos":
                ans=String.valueOf(unitOf.toGalileos());
                break;
            case "GigametersPerSecondSquared":
                ans=String.valueOf(unitOf.toGigametersPerSecondSquared());
                break;
            case "Gravity":
                ans=String.valueOf(unitOf.toGravity());
                break;
            case "HectometersPerSecondSquared":
                ans=String.valueOf(unitOf.toHectometersPerSecondSquared());
                break;
            case "InchesPerMinutePerSecond":
                ans=String.valueOf(unitOf.toInchesPerMinutePerSecond());
                break;
            case "InchesPerSecondSquared":
                ans=String.valueOf(unitOf.toInchesPerSecondSquared());
                break;
            case "KilometersPerHourPerSecond":
                ans=String.valueOf(unitOf.toKilometersPerHourPerSecond());
                break;

            case "KilometersPerHourSquared":
                ans=String.valueOf(unitOf.toKilometersPerHourSquared());
                break;
            case "KilometersPerSecondSquared":
                ans=String.valueOf(unitOf.toKilometersPerSecondSquared());
                break;
            case "KnotsPerSecond":
                ans=String.valueOf(unitOf.toKnotsPerSecond());
                break;
            case "MegametersPerSecondSquared":
                ans=String.valueOf(unitOf.toMegametersPerSecondSquared());
                break;
            case "MetersPerSecondSquared":
                ans=String.valueOf(unitOf.toMetersPerSecondSquared());
                break;
            case "Microgalileos":
                ans=String.valueOf(unitOf.toMicrogalileos());
                break;
            case "MicrometersPerSecondSquared":
                ans=String.valueOf(unitOf.toMicrometersPerSecondSquared());
                break;
            case "MilesPerHourPerSecond":
                ans=String.valueOf(unitOf.toMilesPerHourPerSecond());
                break;
            case "MilesPerMinutePerSecond":
                ans=String.valueOf(unitOf.toMilesPerMinutePerSecond());
                break;
            case "MilesPerSecondSquared":
                ans=String.valueOf(unitOf.toMilesPerSecondSquared());
                break;
            case "Milligalileos":
                ans=String.valueOf(unitOf.toMilligalileos());
                break;
            case "MillimetersPerSecondSquared":
                ans=String.valueOf(unitOf.toMillimetersPerSecondSquared());
                break;
            case "NanometersPerSecondSquared":
                ans=String.valueOf(unitOf.toNanometersPerSecondSquared());
                break;
            case "PetametersPerSecondSquared":
                ans=String.valueOf(unitOf.toPetametersPerSecondSquared());
                break;
            case "PicometersPerSecondSquared":
                ans=String.valueOf(unitOf.toPicometersPerSecondSquared());
                break;
            case "TerametersPerSecondSquared":
                ans=String.valueOf(unitOf.toTerametersPerSecondSquared());
                break;
            case "YardsPerSecondSquared":
                ans=String.valueOf(unitOf.toYardsPerSecondSquared());
                break;

        }
        return ans;
    }

    private String Angel(String value,String value_unit,String answer_unit){
        String ans="0";
        UnitOf.Angle unitOf;

        switch (value_unit){

            case "Degrees":
                unitOf=new UnitOf.Angle().fromDegrees(Double.valueOf(value));
                ans=AngelHelper(answer_unit,unitOf);
                break;
            case "Gradians":
                unitOf=new UnitOf.Angle().fromGradians(Double.valueOf(value));
                ans=AngelHelper(answer_unit,unitOf);
                break;
            case "Mils":
                unitOf=new UnitOf.Angle().fromMils(Double.valueOf(value));
                ans=AngelHelper(answer_unit,unitOf);
                break;
            case "Minutes":
                unitOf=new UnitOf.Angle().fromMinutes(Double.valueOf(value));
                ans=AngelHelper(answer_unit,unitOf);
                break;
            case "Quadrants":
                unitOf=new UnitOf.Angle().fromQuadrants(Double.valueOf(value));
                ans=AngelHelper(answer_unit,unitOf);
                break;
            case "Radians":
                unitOf=new UnitOf.Angle().fromRadians(Double.valueOf(value));
                ans=AngelHelper(answer_unit,unitOf);
                break;
            case "Revolutions":
                unitOf=new UnitOf.Angle().fromRevolutions(Double.valueOf(value));
                ans=AngelHelper(answer_unit,unitOf);
                break;
            case "RightAngles":
                unitOf=new UnitOf.Angle().fromRightAngles(Double.valueOf(value));
                ans=AngelHelper(answer_unit,unitOf);
                break;
            case "Seconds":
                unitOf=new UnitOf.Angle().fromSeconds(Double.valueOf(value));
                ans=AngelHelper(answer_unit,unitOf);
                break;
            case "Sextants":
                unitOf=new UnitOf.Angle().fromSextants(Double.valueOf(value));
                ans=AngelHelper(answer_unit,unitOf);
                break;
            case "Signs":
                unitOf=new UnitOf.Angle().fromSigns(Double.valueOf(value));
                ans=AngelHelper(answer_unit,unitOf);
                break;
            case "Turns":
                unitOf=new UnitOf.Angle().fromTurns(Double.valueOf(value));
                ans=AngelHelper(answer_unit,unitOf);
                break;

        }
        return ans;
    }

    private String AngelHelper(String answer_unit,UnitOf.Angle unitOf){
        String ans="0";

        switch (answer_unit){

            case "Degrees":
                ans=String.valueOf(unitOf.toDegrees());
                break;

            case "Gradians":
                ans=String.valueOf(unitOf.toGradians());
                break;
            case "Mils":
                ans=String.valueOf(unitOf.toMils());
                break;
            case "Minutes":
                ans=String.valueOf(unitOf.toMinutes());
                break;
            case "Quadrants":
                ans=String.valueOf(unitOf.toQuadrants());
                break;
            case "Radians":
                ans=String.valueOf(unitOf.toRadians());
                break;
            case "Revolutions":
                ans=String.valueOf(unitOf.toRevolutions());
                break;
            case "RightAngles":
                ans=String.valueOf(unitOf.toRightAngles());
                break;
            case "Seconds":
                ans=String.valueOf(unitOf.toSeconds());
                break;
            case "Sextants":
                ans=String.valueOf(unitOf.toSextants());
                break;
            case "Signs":
                ans=String.valueOf(unitOf.toSigns());
                break;
            case "Turns":
                ans=String.valueOf(unitOf.toTurns());
                break;

        }
        return ans;
    }

    private String Area(String value,String value_unit,String answer_unit){
        String ans="0";
        UnitOf.Area unitOf;
        
        switch (value_unit){

            case "Are":
                unitOf=new UnitOf.Area().fromAres(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "Acre":
                unitOf=new UnitOf.Area().fromArpents(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "Barns":
                unitOf=new UnitOf.Area().fromBarns(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "CircularInches":
                unitOf=new UnitOf.Area().fromCircularInches(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "CircularMils":
                unitOf=new UnitOf.Area().fromCircularMils(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "Hectare":
                unitOf=new UnitOf.Area().fromHectares(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "Homesteads":
                unitOf=new UnitOf.Area().fromHomesteads(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "Roods":
                unitOf=new UnitOf.Area().fromRoods(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "Sabins":
                unitOf=new UnitOf.Area().fromSabins(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "cm2":
                unitOf=new UnitOf.Area().fromSquareCentimeters(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "SquareChains":
                unitOf=new UnitOf.Area().fromSquareChains(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "SquareDecimeters":
                unitOf=new UnitOf.Area().fromSquareDecimeters(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "ft2":
                unitOf=new UnitOf.Area().fromSquareFeet(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "SquareHectometers":
                unitOf=new UnitOf.Area().fromSquareHectometers(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "in2":
                unitOf=new UnitOf.Area().fromSquareInches(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "km2":
                unitOf=new UnitOf.Area().fromSquareKilometers(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "m2":
                unitOf=new UnitOf.Area().fromSquareMeters(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "SquareMicrometers":
                unitOf=new UnitOf.Area().fromSquareMicrometers(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "mile2":
                unitOf=new UnitOf.Area().fromSquareMiles(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "mm2":
                unitOf=new UnitOf.Area().fromSquareMillimeters(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "SquareNanometers":
                unitOf=new UnitOf.Area().fromSquareNanometers(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "SquarePerches":
                unitOf=new UnitOf.Area().fromSquarePerches(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "SquarePoles":
                unitOf=new UnitOf.Area().fromSquarePoles(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "SquareRods":
                unitOf=new UnitOf.Area().fromSquareRods(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "yare2":
                unitOf=new UnitOf.Area().fromSquareYards(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;

        }
        return ans;
    }

    private String AreaHelper(String answer_unit,UnitOf.Area unitOf){
        String ans="0";
        
        switch (answer_unit){

            case"Are":
                ans=String.valueOf(unitOf.toAres());
                break;
            case"Acre":
                ans=String.valueOf(unitOf.toArpents());
                break;
            case"Barns":
                ans=String.valueOf(unitOf.toBarns());
                break;
            case"CircularInches":
                ans=String.valueOf(unitOf.toCircularInches());
                break;
            case"CircularMils":
                ans=String.valueOf(unitOf.toCircularMils());
                break;
            case"Hectare":
                ans=String.valueOf(unitOf.toHectares());
                break;
            case"Homesteads":
                ans=String.valueOf(unitOf.toHomesteads());
                break;
            case"Roods":
                ans=String.valueOf(unitOf.toRoods());
                break;
            case"Sabins":
                ans=String.valueOf(unitOf.toSabins());
                break;
            case"cm2":
                ans=String.valueOf(unitOf.toSquareCentimeters());
                break;
            case"SquareChains":
                ans=String.valueOf(unitOf.toSquareChains());
                break;
            case"SquareDecimeters":
                ans=String.valueOf(unitOf.toSquareDecimeters());
                break;
            case"SquareDekameters":
                ans=String.valueOf(unitOf.toSquareDekameters());
                break;
            case"ft2":
                ans=String.valueOf(unitOf.toSquareFeet());
                break;
            case"SquareHectometers":
                ans=String.valueOf(unitOf.toSquareHectometers());
                break;
            case"in2":
                ans=String.valueOf(unitOf.toSquareInches());
                break;
            case"km2":
                ans=String.valueOf(unitOf.toSquareKilometers());
                break;
            case"m2":
                ans=String.valueOf(unitOf.toSquareMeters());
                break;
            case"SquareMicrometers":
                ans=String.valueOf(unitOf.toSquareMicrometers());
                break;
            case"mile2":
                ans=String.valueOf(unitOf.toSquareMiles());
                break;
            case"mm2":
                ans=String.valueOf(unitOf.toSquareMillimeters());
                break;
            case"SquareNanometers":
                ans=String.valueOf(unitOf.toSquareNanometers());
                break;
            case"SquarePerches":
                ans=String.valueOf(unitOf.toSquarePerches());
                break;
            case"SquarePoles":
                ans=String.valueOf(unitOf.toSquarePoles());
                break;
            case"SquareRods":
                ans=String.valueOf(unitOf.toSquareRods());
                break;
            case"yare2":
                ans=String.valueOf(unitOf.toSquareYards());
                break;

        }
        return ans;
    }

    private String Distance(String value,String value_unit,String answer_unit){
        String ans="0";
        UnitOf.Length unitOf;
        switch (value_unit){

            case "Attometers":
                unitOf=new UnitOf.Length().fromAttometers(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Barleycorns":
                unitOf=new UnitOf.Length().fromBarleycorns(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "CablesImperial":
                unitOf=new UnitOf.Length().fromCablesImperial(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "CablesInternational":
                unitOf=new UnitOf.Length().fromCablesInternational(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "CablesUSCustomary":
                unitOf=new UnitOf.Length().fromCablesUSCustomary(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Caliber":
                unitOf=new UnitOf.Length().fromCaliber(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Centiinches":
                unitOf=new UnitOf.Length().fromCentiinches(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "cm":
                unitOf=new UnitOf.Length().fromCentimeters(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Chains":
                unitOf=new UnitOf.Length().fromChains(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Cubits":
                unitOf=new UnitOf.Length().fromCubits(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Decimeters":
                unitOf=new UnitOf.Length().fromDecimeters(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Dekameters":
                unitOf=new UnitOf.Length().fromDekameters(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Ells":
                unitOf=new UnitOf.Length().fromElls(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Exameters":
                unitOf=new UnitOf.Length().fromExameters(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Fathoms":
                unitOf=new UnitOf.Length().fromFathoms(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "ft":
                unitOf=new UnitOf.Length().fromFeet(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Femtometers":
                unitOf=new UnitOf.Length().fromFemtometers(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Fingers":
                unitOf=new UnitOf.Length().fromFingers(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Furlongs":
                unitOf=new UnitOf.Length().fromFurlongs(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Gigameters":
                unitOf=new UnitOf.Length().fromGigameters(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Hands":
                unitOf=new UnitOf.Length().fromHands(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Hectometers":
                unitOf=new UnitOf.Length().fromHectometers(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "in":
                unitOf=new UnitOf.Length().fromInches(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "km":
                unitOf=new UnitOf.Length().fromKilometers(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Kiloyards":
                unitOf=new UnitOf.Length().fromKiloyards(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Leagues":
                unitOf=new UnitOf.Length().fromLeagues(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "LightYears":
                unitOf=new UnitOf.Length().fromLightYears(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Links":
                unitOf=new UnitOf.Length().fromLinks(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Megameters":
                unitOf=new UnitOf.Length().fromMegameters(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "m":
                unitOf=new UnitOf.Length().fromMeters(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Microinches":
                unitOf=new UnitOf.Length().fromMicroinches(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Micrometers":
                unitOf=new UnitOf.Length().fromMicrometers(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Microns":
                unitOf=new UnitOf.Length().fromMicrons(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Miles":
                unitOf=new UnitOf.Length().fromMiles(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "mm":
                unitOf=new UnitOf.Length().fromMillimeters(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Nails":
                unitOf=new UnitOf.Length().fromNails(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Nanometers":
                unitOf=new UnitOf.Length().fromNanometers(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "NauticalLeaguesInternational":
                unitOf=new UnitOf.Length().fromNauticalLeaguesInternational(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "NauticalLeaguesUK":
                unitOf=new UnitOf.Length().fromNauticalLeaguesUK(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "NauticalMilesInternational":
                unitOf=new UnitOf.Length().fromNauticalMilesInternational(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "NauticalMilesUK":
                unitOf=new UnitOf.Length().fromNauticalMilesUK(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "NauticalMilesUSCustomary":
                unitOf=new UnitOf.Length().fromNauticalMilesUSCustomary(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Perches":
                unitOf=new UnitOf.Length().fromPerches(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Petameters":
                unitOf=new UnitOf.Length().fromPetameters(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Picometers":
                unitOf=new UnitOf.Length().fromPicometers(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Poles":
                unitOf=new UnitOf.Length().fromPoles(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Rods":
                unitOf=new UnitOf.Length().fromRods(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Ropes":
                unitOf=new UnitOf.Length().fromRopes(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Spans":
                unitOf=new UnitOf.Length().fromSpans(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Terameters":
                unitOf=new UnitOf.Length().fromTerameters(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "ThousandthInches":
                unitOf=new UnitOf.Length().fromThousandthInches(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Yards":
                unitOf=new UnitOf.Length().fromYards(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;

        }
        return ans;
    }

    private String DistanceHelper(String answer_unit,UnitOf.Length unitOf){
        String ans="0";
        
        switch (answer_unit){

            case "Attometers":
                ans=String.valueOf(unitOf.toAttometers());
                break;
            case "Barleycorns":
                ans=String.valueOf(unitOf.toBarleycorns());
                break;
            case "CablesImperial":
                ans=String.valueOf(unitOf.toCablesImperial());
                break;
            case "CablesInternational":
                ans=String.valueOf(unitOf.toCablesInternational());
                break;
            case "CablesUSCustomary":
                ans=String.valueOf(unitOf.toCablesUSCustomary());
                break;
            case "Caliber":
                ans=String.valueOf(unitOf.toCaliber());
                break;
            case "Centiinches":
                ans=String.valueOf(unitOf.toCentiinches());
                break;
            case "cm":
                ans=String.valueOf(unitOf.toCentimeters());
                break;
            case "Chains":
                ans=String.valueOf(unitOf.toChains());
                break;
            case "Cubits":
                ans=String.valueOf(unitOf.toCubits());
                break;
            case "Decimeters":
                ans=String.valueOf(unitOf.toDecimeters());
                break;
            case "Dekameters":
                ans=String.valueOf(unitOf.toDekameters());
                break;
            case "Ells":
                ans=String.valueOf(unitOf.toElls());
                break;
            case "Exameters":
                ans=String.valueOf(unitOf.toExameters());
                break;
            case "Fathoms":
                ans=String.valueOf(unitOf.toFathoms());
                break;
            case "ft":
                ans=String.valueOf(unitOf.toFeet());
                break;
            case "Femtometers":
                ans=String.valueOf(unitOf.toFemtometers());
                break;
            case "Fingers":
                ans=String.valueOf(unitOf.toFingers());
                break;
            case "Furlongs":
                ans=String.valueOf(unitOf.toFurlongs());
                break;
            case "Gigameters":
                ans=String.valueOf(unitOf.toGigameters());
                break;
            case "Hands":
                ans=String.valueOf(unitOf.toHands());
                break;
            case "Hectometers":
                ans=String.valueOf(unitOf.toHectometers());
                break;
            case "in":
                ans=String.valueOf(unitOf.toInches());
                break;
            case "km":
                ans=String.valueOf(unitOf.toKilometers());
                break;
            case "Kiloyards":
                ans=String.valueOf(unitOf.toKiloyards());
                break;
            case "Leagues":
                ans=String.valueOf(unitOf.toLeagues());
                break;
            case "LightYears":
                ans=String.valueOf(unitOf.toLightYears());
                break;
            case "Links":
                ans=String.valueOf(unitOf.toLinks());
                break;
            case "Megameters":
                ans=String.valueOf(unitOf.toMegameters());
                break;
            case "m":
                ans=String.valueOf(unitOf.toMeters());
                break;
            case "Microinches":
                ans=String.valueOf(unitOf.toMicroinches());
                break;
            case "Micrometers":
                ans=String.valueOf(unitOf.toMicrometers());
                break;
            case "Microns":
                ans=String.valueOf(unitOf.toMicrons());
                break;
            case "Miles":
                ans=String.valueOf(unitOf.toMiles());
                break;
            case "mm":
                ans=String.valueOf(unitOf.toMillimeters());
                break;
            case "Nails":
                ans=String.valueOf(unitOf.toNails());
                break;
            case "Nanometers":
                ans=String.valueOf(unitOf.toNanometers());
                break;
            case "NauticalLeaguesInternational":
                ans=String.valueOf(unitOf.toNauticalLeaguesInternational());
                break;
            case "NauticalLeaguesUK":
                ans=String.valueOf(unitOf.toNauticalLeaguesUK());
                break;
            case "NauticalMilesInternational":
                ans=String.valueOf(unitOf.toNauticalMilesInternational());
                break;
            case "NauticalMilesUK":
                ans=String.valueOf(unitOf.toNauticalMilesUK());
                break;
            case "NauticalMilesUSCustomary":
                ans=String.valueOf(unitOf.toNauticalMilesUSCustomary());
                break;
            case "Perches":
                ans=String.valueOf(unitOf.toPerches());
                break;
            case "Petameters":
                ans=String.valueOf(unitOf.toPetameters());
                break;
            case "Picometers":
                ans=String.valueOf(unitOf.toPicometers());
                break;
            case "Poles":
                ans=String.valueOf(unitOf.toPoles());
                break;
            case "Rods":
                ans=String.valueOf(unitOf.toRods());
                break;
            case "Ropes":
                ans=String.valueOf(unitOf.toRopes());
                break;
            case "Spans":
                ans=String.valueOf(unitOf.toSpans());
                break;
            case "Terameters":
                ans=String.valueOf(unitOf.toTerameters());
                break;
            case "ThousandthInches":
                ans=String.valueOf(unitOf.toThousandthInches());
                break;
            case "Yards":
                ans=String.valueOf(unitOf.toYards());
                break;

        }
        return ans;
    }

    private String Enegy(String value,String value_unit,String answer_unit){
        String ans="0";
        UnitOf.Energy unitOf;
        switch (value_unit){

            case "BTUsInternationalStandard":
                unitOf=new UnitOf.Energy().fromBTUsInternationalStandard(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "BTUsThermochemical":
                unitOf=new UnitOf.Energy().fromBTUsThermochemical(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "BarrelsOfOilEquivalent":
                unitOf=new UnitOf.Energy().fromBarrelsOfOilEquivalent(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "CaloriesInternationalSteam":
                unitOf=new UnitOf.Energy().fromCaloriesInternationalSteam(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "CaloriesNutritional":
                unitOf=new UnitOf.Energy().fromCaloriesNutritional(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "CaloriesThermochemical":
                unitOf=new UnitOf.Energy().fromCaloriesThermochemical(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "DyneCentimeters":
                unitOf=new UnitOf.Energy().fromDyneCentimeters(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "ElectronVolts":
                unitOf=new UnitOf.Energy().fromElectronVolts(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "Ergs":
                unitOf=new UnitOf.Energy().fromErgs(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "FootPounds":
                unitOf=new UnitOf.Energy().fromFootPounds(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "Gigajoules":
                unitOf=new UnitOf.Energy().fromGigajoules(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "GigatonsOfTNT":
                unitOf=new UnitOf.Energy().fromGigatonsOfTNT(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "GigawattHours":
                unitOf=new UnitOf.Energy().fromGigawattHours(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "GramForceCentimeters":
                unitOf=new UnitOf.Energy().fromGramForceCentimeters(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "GramForceMeters":
                unitOf=new UnitOf.Energy().fromGramForceMeters(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "Hartrees":
                unitOf=new UnitOf.Energy().fromHartrees(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "HorsepowerHours":
                unitOf=new UnitOf.Energy().fromHorsepowerHours(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "HorsepowerHoursMetric":
                unitOf=new UnitOf.Energy().fromHorsepowerHoursMetric(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "InchOunces":
                unitOf=new UnitOf.Energy().fromInchOunces(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "InchPounds":
                unitOf=new UnitOf.Energy().fromInchPounds(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "Joules":
                unitOf=new UnitOf.Energy().fromJoules(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "KilocaloriesInternationalSteam":
                unitOf=new UnitOf.Energy().fromKilocaloriesInternationalSteam(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "KilocaloriesThermochemical":
                unitOf=new UnitOf.Energy().fromKilocaloriesThermochemical(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "KiloelectronVolts":
                unitOf=new UnitOf.Energy().fromKiloelectronVolts(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "KilogramForceCentimeters":
                unitOf=new UnitOf.Energy().fromKilogramForceCentimeters(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "KilogramForceMeters":
                unitOf=new UnitOf.Energy().fromKilogramForceMeters(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "KilogramsOfTNT":
                unitOf=new UnitOf.Energy().fromKilogramsOfTNT(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "Kilojoules":
                unitOf=new UnitOf.Energy().fromKilojoules(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "KilopondMeters":
                unitOf=new UnitOf.Energy().fromKilopondMeters(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "KilotonsOfTNT":
                unitOf=new UnitOf.Energy().fromKilotonsOfTNT(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "KilowattHours":
                unitOf=new UnitOf.Energy().fromKilowattHours(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "KilowattSeconds":
                unitOf=new UnitOf.Energy().fromKilowattSeconds(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "LiterAtmospheres":
                unitOf=new UnitOf.Energy().fromLiterAtmospheres(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "MegaBTUsInternationalStandard":
                unitOf=new UnitOf.Energy().fromMegaBTUsInternationalStandard(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "MegaelectronVolts":
                unitOf=new UnitOf.Energy().fromMegaelectronVolts(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "Megajoules":
                unitOf=new UnitOf.Energy().fromMegajoules(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "MegatonsOfTNT":
                unitOf=new UnitOf.Energy().fromMegatonsOfTNT(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "MegawattHours":
                unitOf=new UnitOf.Energy().fromMegawattHours(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "Microjoules":
                unitOf=new UnitOf.Energy().fromMicrojoules(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "Millijoules":
                unitOf=new UnitOf.Energy().fromMillijoules(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "Nanojoules":
                unitOf=new UnitOf.Energy().fromNanojoules(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "NewtonMeters":
                unitOf=new UnitOf.Energy().fromNewtonMeters(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "PlanckEnergy":
                unitOf=new UnitOf.Energy().fromPlanckEnergy(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "PoundalFeet":
                unitOf=new UnitOf.Energy().fromPoundalFeet(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "Rydbergs":
                unitOf=new UnitOf.Energy().fromRydbergs(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "ThermsEC":
                unitOf=new UnitOf.Energy().fromThermsEC(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "ThermsUS":
                unitOf=new UnitOf.Energy().fromThermsUS(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "TonsOfTNT":
                unitOf=new UnitOf.Energy().fromTonsOfTNT(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "WattHours":
                unitOf=new UnitOf.Energy().fromWattHours(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;
            case "WattSeconds":
                unitOf=new UnitOf.Energy().fromWattSeconds(Double.valueOf(value));
                ans=EnergyHelper(answer_unit,unitOf);
                break;

        }
        return ans;
    }

    private String EnergyHelper(String answer_unit,UnitOf.Energy unitOf){
        String ans="0";

        switch (answer_unit){

            case "BTUsInternationalStandard":
                ans=String.valueOf(unitOf.toBTUsInternationalStandard());
                break;
            case "BTUsThermochemical":
                ans=String.valueOf(unitOf.toBTUsThermochemical());
                break;
            case "BarrelsOfOilEquivalent":
                ans=String.valueOf(unitOf.toBarrelsOfOilEquivalent());
                break;
            case "CaloriesInternationalSteam":
                ans=String.valueOf(unitOf.toCaloriesInternationalSteam());
                break;
            case "CaloriesNutritional":
                ans=String.valueOf(unitOf.toCaloriesNutritional());
                break;
            case "CaloriesThermochemical":
                ans=String.valueOf(unitOf.toCaloriesThermochemical());
                break;
            case "DyneCentimeters":
                ans=String.valueOf(unitOf.toDyneCentimeters());
                break;
            case "ElectronVolts":
                ans=String.valueOf(unitOf.toElectronVolts());
                break;
            case "Ergs":
                ans=String.valueOf(unitOf.toErgs());
                break;
            case "FootPounds":
                ans=String.valueOf(unitOf.toFootPounds());
                break;
            case "Gigajoules":
                ans=String.valueOf(unitOf.toGigajoules());
                break;
            case "GigatonsOfTNT":
                ans=String.valueOf(unitOf.toGigatonsOfTNT());
                break;
            case "GigawattHours":
                ans=String.valueOf(unitOf.toGigawattHours());
                break;
            case "GramForceCentimeters":
                ans=String.valueOf(unitOf.toGramForceCentimeters());
                break;
            case "GramForceMeters":
                ans=String.valueOf(unitOf.toGramForceMeters());
                break;
            case "Hartrees":
                ans=String.valueOf(unitOf.toHartrees());
                break;
            case "HorsepowerHours":
                ans=String.valueOf(unitOf.toHorsepowerHours());
                break;
            case "HorsepowerHoursMetric":
                ans=String.valueOf(unitOf.toHorsepowerHoursMetric());
                break;
            case "InchOunces":
                ans=String.valueOf(unitOf.toInchOunces());
                break;
            case "InchPounds":
                ans=String.valueOf(unitOf.toInchPounds());
                break;
            case "Joules":
                ans=String.valueOf(unitOf.toJoules());
                break;
            case "KilocaloriesInternationalSteam":
                ans=String.valueOf(unitOf.toKilocaloriesInternationalSteam());
                break;
            case "KilocaloriesThermochemical":
                ans=String.valueOf(unitOf.toKilocaloriesThermochemical());
                break;
            case "KiloelectronVolts":
                ans=String.valueOf(unitOf.toKiloelectronVolts());
                break;
            case "KilogramForceCentimeters":
                ans=String.valueOf(unitOf.toKilogramForceCentimeters());
                break;
            case "KilogramForceMeters":
                ans=String.valueOf(unitOf.toKilogramForceMeters());
                break;
            case "KilogramsOfTNT":
                ans=String.valueOf(unitOf.toKilogramsOfTNT());
                break;
            case "Kilojoules":
                ans=String.valueOf(unitOf.toKilojoules());
                break;
            case "KilopondMeters":
                ans=String.valueOf(unitOf.toKilopondMeters());
                break;
            case "KilotonsOfTNT":
                ans=String.valueOf(unitOf.toKilotonsOfTNT());
                break;
            case "KilowattHours":
                ans=String.valueOf(unitOf.toKilowattHours());
                break;
            case "KilowattSeconds":
                ans=String.valueOf(unitOf.toKilowattSeconds());
                break;
            case "LiterAtmospheres":
                ans=String.valueOf(unitOf.toLiterAtmospheres());
                break;
            case "MegaBTUsInternationalStandard":
                ans=String.valueOf(unitOf.toMegaBTUsInternationalStandard());
                break;
            case "MegaelectronVolts":
                ans=String.valueOf(unitOf.toMegaelectronVolts());
                break;
            case "Megajoules":
                ans=String.valueOf(unitOf.toMegajoules());
                break;
            case "MegatonsOfTNT":
                ans=String.valueOf(unitOf.toMegatonsOfTNT());
                break;
            case "MegawattHours":
                ans=String.valueOf(unitOf.toMegawattHours());
                break;
            case "Microjoules":
                ans=String.valueOf(unitOf.toMicrojoules());
                break;
            case "Millijoules":
                ans=String.valueOf(unitOf.toMillijoules());
                break;
            case "Nanojoules":
                ans=String.valueOf(unitOf.toNanojoules());
                break;
            case "NewtonMeters":
                ans=String.valueOf(unitOf.toNewtonMeters());
                break;
            case "PlanckEnergy":
                ans=String.valueOf(unitOf.toPlanckEnergy());
                break;
            case "PoundalFeet":
                ans=String.valueOf(unitOf.toPoundalFeet());
                break;
            case "Rydbergs":
                ans=String.valueOf(unitOf.toRydbergs());
                break;
            case "ThermsEC":
                ans=String.valueOf(unitOf.toThermsEC());
                break;
            case "ThermsUS":
                ans=String.valueOf(unitOf.toThermsUS());
                break;
            case "TonsOfTNT":
                ans=String.valueOf(unitOf.toTonsOfTNT());
                break;
            case "WattHours":
                ans=String.valueOf(unitOf.toWattHours());
                break;
            case "WattSeconds":
                ans=String.valueOf(unitOf.toWattSeconds());
                break;

        }
        return ans;
    }

    private String FerequenceHelper(String answer_unit,UnitOf.Frequency unitOf){
        String ans="0";

        switch (answer_unit){

            case "Attohertz":
                ans=String.valueOf(unitOf.toAttohertz());
                break;
            case "Centihertz":
                ans=String.valueOf(unitOf.toCentihertz());
                break;
            case "CyclesPerSecond":
                ans=String.valueOf(unitOf.toCyclesPerSecond());
                break;
            case "Decihertz":
                ans=String.valueOf(unitOf.toDecihertz());
                break;
            case "Dekahertz":
                ans=String.valueOf(unitOf.toDekahertz());
                break;
            case "Exahertz":
                ans=String.valueOf(unitOf.toExahertz());
                break;
            case "Femtohertz":
                ans=String.valueOf(unitOf.toFemtohertz());
                break;
            case "Gigahertz":
                ans=String.valueOf(unitOf.toGigahertz());
                break;
            case "Hectohertz":
                ans=String.valueOf(unitOf.toHectohertz());
                break;
            case "Hertz":
                ans=String.valueOf(unitOf.toHertz());
                break;
            case "Kilohertz":
                ans=String.valueOf(unitOf.toKilohertz());
                break;
            case "Megahertz":
                ans=String.valueOf(unitOf.toMegahertz());
                break;
            case "Microhertz":
                ans=String.valueOf(unitOf.toMicrohertz());
                break;
            case "Millihertz":
                ans=String.valueOf(unitOf.toMillihertz());
                break;
            case "Nanohertz":
                ans=String.valueOf(unitOf.toNanohertz());
                break;
            case "Petahertz":
                ans=String.valueOf(unitOf.toPetahertz());
                break;
            case "Picohertz":
                ans=String.valueOf(unitOf.toPicohertz());
                break;
            case "RevolutionsPerDay":
                ans=String.valueOf(unitOf.toRevolutionsPerDay());
                break;
            case "RevolutionsPerHour":
                ans=String.valueOf(unitOf.toRevolutionsPerHour());
                break;
            case "RevolutionsPerMinute":
                ans=String.valueOf(unitOf.toRevolutionsPerMinute());
                break;
            case "RevolutionsPerSecond":
                ans=String.valueOf(unitOf.toRevolutionsPerSecond());
                break;
            case "Terahertz":
                ans=String.valueOf(unitOf.toTerahertz());
                break;

        }
        return ans;
    }

    private String Ferequence(String value,String value_unit,String answer_unit){
        String ans="0";
        UnitOf.Frequency unitOf;

        switch (value_unit){

            case "Attohertz":
                unitOf=new UnitOf.Frequency().fromAttohertz(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "Centihertz":
                unitOf=new UnitOf.Frequency().fromCentihertz(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "CyclesPerSecond":
                unitOf=new UnitOf.Frequency().fromCyclesPerSecond(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "Decihertz":
                unitOf=new UnitOf.Frequency().fromDecihertz(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "Dekahertz":
                unitOf=new UnitOf.Frequency().fromDekahertz(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "Exahertz":
                unitOf=new UnitOf.Frequency().fromExahertz(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "Femtohertz":
                unitOf=new UnitOf.Frequency().fromFemtohertz(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "Gigahertz":
                unitOf=new UnitOf.Frequency().fromGigahertz(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "Hectohertz":
                unitOf=new UnitOf.Frequency().fromHectohertz(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "Hertz":
                unitOf=new UnitOf.Frequency().fromHertz(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "Kilohertz":
                unitOf=new UnitOf.Frequency().fromKilohertz(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "Megahertz":
                unitOf=new UnitOf.Frequency().fromMegahertz(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "Microhertz":
                unitOf=new UnitOf.Frequency().fromMicrohertz(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "Millihertz":
                unitOf=new UnitOf.Frequency().fromMillihertz(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "Nanohertz":
                unitOf=new UnitOf.Frequency().fromNanohertz(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "Petahertz":
                unitOf=new UnitOf.Frequency().fromPetahertz(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "Picohertz":
                unitOf=new UnitOf.Frequency().fromPicohertz(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "RevolutionsPerDay":
                unitOf=new UnitOf.Frequency().fromRevolutionsPerDay(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "RevolutionsPerHour":
                unitOf=new UnitOf.Frequency().fromRevolutionsPerHour(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "RevolutionsPerMinute":
                unitOf=new UnitOf.Frequency().fromRevolutionsPerMinute(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "RevolutionsPerSecond":
                unitOf=new UnitOf.Frequency().fromRevolutionsPerSecond(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;
            case "Terahertz":
                unitOf=new UnitOf.Frequency().fromTerahertz(Double.valueOf(value));
                ans=FerequenceHelper(answer_unit,unitOf);
                break;

        }
        return ans;
    }

    private String ForceHelper(String answer_unit,UnitOf.Force unitOf){
        String ans="0";
        
        switch (answer_unit){

            case "Attonewtons":
                ans=String.valueOf(unitOf.toAttonewtons());
                break;
            case "Centinewtons":
                ans=String.valueOf(unitOf.toCentinewtons());
                break;
            case "Decinewtons":
                ans=String.valueOf(unitOf.toDecinewtons());
                break;
            case "Dekanewtons":
                ans=String.valueOf(unitOf.toDekanewtons());
                break;
            case "Dynes":
                ans=String.valueOf(unitOf.toDynes());
                break;
            case "Exanewtons":
                ans=String.valueOf(unitOf.toExanewtons());
                break;
            case "Femtonewtons":
                ans=String.valueOf(unitOf.toFemtonewtons());
                break;
            case "Giganewtons":
                ans=String.valueOf(unitOf.toGiganewtons());
                break;
            case "GramForces":
                ans=String.valueOf(unitOf.toGramForces());
                break;
            case "GraveForces":
                ans=String.valueOf(unitOf.toGraveForces());
                break;
            case "Hectonewtons":
                ans=String.valueOf(unitOf.toHectonewtons());
                break;
            case "JouleCentimeters":
                ans=String.valueOf(unitOf.toJouleCentimeters());
                break;
            case "JouleMeters":
                ans=String.valueOf(unitOf.toJouleMeters());
                break;
            case "KilogramForces":
                ans=String.valueOf(unitOf.toKilogramForces());
                break;
            case "Kilonewtons":
                ans=String.valueOf(unitOf.toKilonewtons());
                break;
            case "Kiloponds":
                ans=String.valueOf(unitOf.toKiloponds());
                break;
            case "KilopoundForces":
                ans=String.valueOf(unitOf.toKilopoundForces());
                break;
            case "LongTonForces":
                ans=String.valueOf(unitOf.toLongTonForces());
                break;
            case "Meganewtons":
                ans=String.valueOf(unitOf.toMeganewtons());
                break;
            case "MetricTonForces":
                ans=String.valueOf(unitOf.toMetricTonForces());
                break;
            case "Micronewtons":
                ans=String.valueOf(unitOf.toMicronewtons());
                break;
            case "MilligraveForces":
                ans=String.valueOf(unitOf.toMilligraveForces());
                break;
            case "Millinewtons":
                ans=String.valueOf(unitOf.toMillinewtons());
                break;
            case "Nanonewtons":
                ans=String.valueOf(unitOf.toNanonewtons());
                break;
            case "Newtons":
                ans=String.valueOf(unitOf.toNewtons());
                break;
            case "OunceForces":
                ans=String.valueOf(unitOf.toOunceForces());
                break;
            case "Petanewtons":
                ans=String.valueOf(unitOf.toPetanewtons());
                break;
            case "Piconewtons":
                ans=String.valueOf(unitOf.toPiconewtons());
                break;
            case "Ponds":
                ans=String.valueOf(unitOf.toPonds());
                break;
            case "PoundFeetPerSecondSquared":
                ans=String.valueOf(unitOf.toPoundFeetPerSecondSquared());
                break;
            case "PoundForces":
                ans=String.valueOf(unitOf.toPoundForces());
                break;
            case "Poundals":
                ans=String.valueOf(unitOf.toPoundals());
                break;
            case "ShortTonForces":
                ans=String.valueOf(unitOf.toShortTonForces());
                break;
            case "Sthenes":
                ans=String.valueOf(unitOf.toSthenes());
                break;
            case "Teranewtons":
                ans=String.valueOf(unitOf.toTeranewtons());
                break;

        }
        return ans;
    }

    private String Force(String value,String value_unit,String answer_unit){
        String ans="0";
        UnitOf.Force unitOf;

        switch (value_unit){

            case "Attonewtons":
                unitOf=new UnitOf.Force().fromAttonewtons(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Centinewtons":
                unitOf=new UnitOf.Force().fromCentinewtons(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Decinewtons":
                unitOf=new UnitOf.Force().fromDecinewtons(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Dekanewtons":
                unitOf=new UnitOf.Force().fromDekanewtons(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Dynes":
                unitOf=new UnitOf.Force().fromDynes(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Exanewtons":
                unitOf=new UnitOf.Force().fromExanewtons(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Femtonewtons":
                unitOf=new UnitOf.Force().fromFemtonewtons(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Giganewtons":
                unitOf=new UnitOf.Force().fromGiganewtons(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "GramForces":
                unitOf=new UnitOf.Force().fromGramForces(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "GraveForces":
                unitOf=new UnitOf.Force().fromGraveForces(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Hectonewtons":
                unitOf=new UnitOf.Force().fromHectonewtons(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "JouleCentimeters":
                unitOf=new UnitOf.Force().fromJouleCentimeters(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "JouleMeters":
                unitOf=new UnitOf.Force().fromJouleMeters(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "KilogramForces":
                unitOf=new UnitOf.Force().fromKilogramForces(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Kilonewtons":
                unitOf=new UnitOf.Force().fromKilonewtons(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Kiloponds":
                unitOf=new UnitOf.Force().fromKiloponds(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "KilopoundForces":
                unitOf=new UnitOf.Force().fromKilopoundForces(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "LongTonForces":
                unitOf=new UnitOf.Force().fromLongTonForces(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Meganewtons":
                unitOf=new UnitOf.Force().fromMeganewtons(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "MetricTonForces":
                unitOf=new UnitOf.Force().fromMetricTonForces(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Micronewtons":
                unitOf=new UnitOf.Force().fromMicronewtons(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "MilligraveForces":
                unitOf=new UnitOf.Force().fromMilligraveForces(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Millinewtons":
                unitOf=new UnitOf.Force().fromMillinewtons(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Nanonewtons":
                unitOf=new UnitOf.Force().fromNanonewtons(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Newtons":
                unitOf=new UnitOf.Force().fromNewtons(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "OunceForces":
                unitOf=new UnitOf.Force().fromOunceForces(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Petanewtons":
                unitOf=new UnitOf.Force().fromPetanewtons(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Piconewtons":
                unitOf=new UnitOf.Force().fromPiconewtons(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Ponds":
                unitOf=new UnitOf.Force().fromPonds(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "PoundFeetPerSecondSquared":
                unitOf=new UnitOf.Force().fromPoundFeetPerSecondSquared(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "PoundForces":
                unitOf=new UnitOf.Force().fromPoundForces(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Poundals":
                unitOf=new UnitOf.Force().fromPoundals(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "ShortTonForces":
                unitOf=new UnitOf.Force().fromShortTonForces(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Sthenes":
                unitOf=new UnitOf.Force().fromSthenes(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;
            case "Teranewtons":
                unitOf=new UnitOf.Force().fromTeranewtons(Double.valueOf(value));
                ans=ForceHelper(answer_unit,unitOf);
                break;

        }
        return ans;
    }

    private String MassHelper(String answer_unit,UnitOf.Mass unitOf){
        String ans="0";
        
        switch (answer_unit){

            case "Carats":
                ans=String.valueOf(unitOf.toCarats());
                break;
            case "Centigrams":
                ans=String.valueOf(unitOf.toCentigrams());
                break;
            case "Centners":
                ans=String.valueOf(unitOf.toCentners());
                break;
            case "Decigrams":
                ans=String.valueOf(unitOf.toDecigrams());
                break;
            case "Dekagrams":
                ans=String.valueOf(unitOf.toDekagrams());
                break;
            case "Exagrams":
                ans=String.valueOf(unitOf.toExagrams());
                break;
            case "Femtograms":
                ans=String.valueOf(unitOf.toFemtograms());
                break;
            case "Gamma":
                ans=String.valueOf(unitOf.toGamma());
                break;
            case "Gigagrams":
                ans=String.valueOf(unitOf.toGigagrams());
                break;
            case "Grams":
                ans=String.valueOf(unitOf.toGrams());
                break;
            case "Hectograms":
                ans=String.valueOf(unitOf.toHectograms());
                break;
            case "Kilograms":
                ans=String.valueOf(unitOf.toKilograms());
                break;
            case "Kilopounds":
                ans=String.valueOf(unitOf.toKilopounds());
                break;
            case "KilotonsMetric":
                ans=String.valueOf(unitOf.toKilotonsMetric());
                break;
            case "Megagrams":
                ans=String.valueOf(unitOf.toMegagrams());
                break;
            case "Micrograms":
                ans=String.valueOf(unitOf.toMicrograms());
                break;
            case "Milligrams":
                ans=String.valueOf(unitOf.toMilligrams());
                break;
            case "Nanograms":
                ans=String.valueOf(unitOf.toNanograms());
                break;
            case "OuncesMetric":
                ans=String.valueOf(unitOf.toOuncesMetric());
                break;
            case "OuncesUS":
                ans=String.valueOf(unitOf.toOuncesUS());
                break;
            case "Petagrams":
                ans=String.valueOf(unitOf.toPetagrams());
                break;
            case "Picograms":
                ans=String.valueOf(unitOf.toPicograms());
                break;
            case "c":
                ans=String.valueOf(unitOf.toPicograms());
                break;
            case "Quintals":
                ans=String.valueOf(unitOf.toQuintals());
                break;
            case "Slugs":
                ans=String.valueOf(unitOf.toSlugs());
                break;
            case "StonesUK":
                ans=String.valueOf(unitOf.toStonesUK());
                break;
            case "StonesUS":
                ans=String.valueOf(unitOf.toStonesUS());
                break;
            case "Teragrams":
                ans=String.valueOf(unitOf.toTeragrams());
                break;
            case "TonsImperial":
                ans=String.valueOf(unitOf.toTonsImperial());
                break;
            case "TonsMetric":
                ans=String.valueOf(unitOf.toTonsMetric());
                break;
            case "TonsUS":
                ans=String.valueOf(unitOf.toTonsUS());
                break;

        }
        return ans;
    }

    private String Mass(String value,String value_unit,String answer_unit){
        String ans="0";
        UnitOf.Mass unitOf;
        
        switch (value_unit){

            case "Carats":
                unitOf=new UnitOf.Mass().fromCarats(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Centigrams":
                unitOf=new UnitOf.Mass().fromCentigrams(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Centners":
                unitOf=new UnitOf.Mass().fromCentners(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Decigrams":
                unitOf=new UnitOf.Mass().fromDecigrams(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Dekagrams":
                unitOf=new UnitOf.Mass().fromDekagrams(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Exagrams":
                unitOf=new UnitOf.Mass().fromExagrams(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Femtograms":
                unitOf=new UnitOf.Mass().fromFemtograms(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Gamma":
                unitOf=new UnitOf.Mass().fromGamma(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Gigagrams":
                unitOf=new UnitOf.Mass().fromGigagrams(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Grams":
                unitOf=new UnitOf.Mass().fromGrams(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Hectograms":
                unitOf=new UnitOf.Mass().fromHectograms(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Kilograms":
                unitOf=new UnitOf.Mass().fromKilograms(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Kilopounds":
                unitOf=new UnitOf.Mass().fromKilopounds(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "KilotonsMetric":
                unitOf=new UnitOf.Mass().fromKilotonsMetric(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Megagrams":
                unitOf=new UnitOf.Mass().fromMegagrams(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Micrograms":
                unitOf=new UnitOf.Mass().fromMicrograms(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Milligrams":
                unitOf=new UnitOf.Mass().fromMilligrams(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Nanograms":
                unitOf=new UnitOf.Mass().fromNanograms(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "OuncesMetric":
                unitOf=new UnitOf.Mass().fromOuncesMetric(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "OuncesUS":
                unitOf=new UnitOf.Mass().fromOuncesUS(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Petagrams":
                unitOf=new UnitOf.Mass().fromPetagrams(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Picograms":
                unitOf=new UnitOf.Mass().fromPicograms(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Pounds":
                unitOf=new UnitOf.Mass().fromPounds(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Quintals":
                unitOf=new UnitOf.Mass().fromQuintals(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Slugs":
                unitOf=new UnitOf.Mass().fromSlugs(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "StonesUK":
                unitOf=new UnitOf.Mass().fromStonesUK(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "StonesUS":
                unitOf=new UnitOf.Mass().fromStonesUS(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "Teragrams":
                unitOf=new UnitOf.Mass().fromTeragrams(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "TonsImperial":
                unitOf=new UnitOf.Mass().fromTonsImperial(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "TonsMetric":
                unitOf=new UnitOf.Mass().fromTonsMetric(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;
            case "TonsUS":
                unitOf=new UnitOf.Mass().fromTonsUS(Double.valueOf(value));
                ans=MassHelper(answer_unit,unitOf);
                break;

        }
        return ans;
    }

    private String PressureHelper(String answer_unit,UnitOf.Pressure unitOf){
        String ans="0";
        
        switch (answer_unit){

            case "Bars":
                ans=String.valueOf(unitOf.toBars());
                break;
            case "Baryes":
                ans=String.valueOf(unitOf.toBaryes());
                break;
            case "CentimetersOfMercury0C":
                ans=String.valueOf(unitOf.toCentimetersOfMercury0C());
                break;
            case "CentimetersOfWater4C":
                ans=String.valueOf(unitOf.toCentimetersOfWater4C());
                break;
            case "Centipascals":
                ans=String.valueOf(unitOf.toCentipascals());
                break;
            case "Decibars":
                ans=String.valueOf(unitOf.toDecibars());
                break;
            case "Decipascals":
                ans=String.valueOf(unitOf.toDecipascals());
                break;
            case "Dekapascals":
                ans=String.valueOf(unitOf.toDekapascals());
                break;
            case "DynesPerSquareCentimeter":
                ans=String.valueOf(unitOf.toDynesPerSquareCentimeter());
                break;
            case "Exapascals":
                ans=String.valueOf(unitOf.toExapascals());
                break;
            case "FeetOfSeaWater":
                ans=String.valueOf(unitOf.toFeetOfSeaWater());
                break;
            case "FeetOfWater4C":
                ans=String.valueOf(unitOf.toFeetOfWater4C());
                break;
            case "FeetOfWater60F":
                ans=String.valueOf(unitOf.toFeetOfWater60F());
                break;
            case "Femtopascals":
                ans=String.valueOf(unitOf.toFemtopascals());
                break;
            case "Gigapascals":
                ans=String.valueOf(unitOf.toGigapascals());
                break;
            case "GramsPerSquareCentimeter":
                ans=String.valueOf(unitOf.toGramsPerSquareCentimeter());
                break;
            case "Hectopascals":
                ans=String.valueOf(unitOf.toHectopascals());
                break;
            case "InchesOfMercury32F":
                ans=String.valueOf(unitOf.toInchesOfMercury32F());
                break;
            case "InchesOfMercury60F":
                ans=String.valueOf(unitOf.toInchesOfMercury60F());
                break;
            case "InchesOfWater4C":
                ans=String.valueOf(unitOf.toInchesOfWater4C());
                break;
            case "InchesOfWater60F":
                ans=String.valueOf(unitOf.toInchesOfWater60F());
                break;
            case "KSI":
                ans=String.valueOf(unitOf.toKSI());
                break;
            case "KilogramsPerSquareCentimeter":
                ans=String.valueOf(unitOf.toKilogramsPerSquareCentimeter());
                break;
            case "KilogramsPerSquareMeter":
                ans=String.valueOf(unitOf.toKilogramsPerSquareMeter());
                break;
            case "KilogramsPerSquareMillimeter":
                ans=String.valueOf(unitOf.toKilogramsPerSquareMillimeter());
                break;
            case "KilonewtonsPerSquareMeter":
                ans=String.valueOf(unitOf.toKilonewtonsPerSquareMeter());
                break;
            case "Kilopascals":
                ans=String.valueOf(unitOf.toKilopascals());
                break;
            case "KipsPerSquareInch":
                ans=String.valueOf(unitOf.toKipsPerSquareInch());
                break;
            case "LongTonsPerSquareFoot":
                ans=String.valueOf(unitOf.toLongTonsPerSquareFoot());
                break;
            case "LongTonsPerSquareInch":
                ans=String.valueOf(unitOf.toLongTonsPerSquareInch());
                break;
            case "Megapascals":
                ans=String.valueOf(unitOf.toMegapascals());
                break;
            case "MetersOfSeaWater":
                ans=String.valueOf(unitOf.toMetersOfSeaWater());
                break;
            case "MetersOfWater4C":
                ans=String.valueOf(unitOf.toMetersOfWater4C());
                break;
            case "Microbars":
                ans=String.valueOf(unitOf.toMicrobars());
                break;
            case "Micropascals":
                ans=String.valueOf(unitOf.toMicropascals());
                break;
            case "Millibars":
                ans=String.valueOf(unitOf.toMillibars());
                break;
            case "MillimetersOfMercury0C":
                ans=String.valueOf(unitOf.toMillimetersOfMercury0C());
                break;
            case "MillimetersOfWater4C":
                ans=String.valueOf(unitOf.toMillimetersOfWater4C());
                break;
            case "Millipascals":
                ans=String.valueOf(unitOf.toMillipascals());
                break;
            case "Nanopascals":
                ans=String.valueOf(unitOf.toNanopascals());
                break;
            case "NewtonsPerSquareCentimeter":
                ans=String.valueOf(unitOf.toNewtonsPerSquareCentimeter());
                break;
            case "NewtonsPerSquareMeter":
                ans=String.valueOf(unitOf.toNewtonsPerSquareMeter());
                break;
            case "NewtonsPerSquareMillimeter":
                ans=String.valueOf(unitOf.toNewtonsPerSquareMillimeter());
                break;
            case "PSI":
                ans=String.valueOf(unitOf.toPSI());
                break;
            case "Pascals":
                ans=String.valueOf(unitOf.toPascals());
                break;
            case "Petapascals":
                ans=String.valueOf(unitOf.toPetapascals());
                break;
            case "Picopascals":
                ans=String.valueOf(unitOf.toPicopascals());
                break;
            case "Pieze":
                ans=String.valueOf(unitOf.toPieze());
                break;
            case "PoundalsPerSquareFoot":
                ans=String.valueOf(unitOf.toPoundalsPerSquareFoot());
                break;
            case "PoundsPerSquareFoot":
                ans=String.valueOf(unitOf.toPoundsPerSquareFoot());
                break;
            case "PoundsPerSquareInch":
                ans=String.valueOf(unitOf.toPoundsPerSquareInch());
                break;
            case "ShortTonsPerSquareFoot":
                ans=String.valueOf(unitOf.toShortTonsPerSquareFoot());
                break;
            case "ShortTonsPerSquareInch":
                ans=String.valueOf(unitOf.toShortTonsPerSquareInch());
                break;
            case "StandardAtmospheres":
                ans=String.valueOf(unitOf.toStandardAtmospheres());
                break;
            case "SthenesPerSquareMeter":
                ans=String.valueOf(unitOf.toSthenesPerSquareMeter());
                break;
            case "TechnicalAtmospheres":
                ans=String.valueOf(unitOf.toTechnicalAtmospheres());
                break;
            case "Terapascals":
                ans=String.valueOf(unitOf.toTerapascals());
                break;
            case "Torrs":
                ans=String.valueOf(unitOf.toTorrs());
                break;

        }
        return ans;
    }

    private String Pressure(String value,String value_unit,String answer_unit){
        String ans="0";
        UnitOf.Pressure unitOf;
        
        switch (value_unit){

            case "Bars":
                unitOf=new UnitOf.Pressure().fromBars(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Baryes":
                unitOf=new UnitOf.Pressure().fromBaryes(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "CentimetersOfMercury0C":
                unitOf=new UnitOf.Pressure().fromCentimetersOfMercury0C(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "CentimetersOfWater4C":
                unitOf=new UnitOf.Pressure().fromCentimetersOfWater4C(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Centipascals":
                unitOf=new UnitOf.Pressure().fromCentipascals(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Decibars":
                unitOf=new UnitOf.Pressure().fromDecibars(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Decipascals":
                unitOf=new UnitOf.Pressure().fromDecipascals(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Dekapascals":
                unitOf=new UnitOf.Pressure().fromDekapascals(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "DynesPerSquareCentimeter":
                unitOf=new UnitOf.Pressure().fromDynesPerSquareCentimeter(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Exapascals":
                unitOf=new UnitOf.Pressure().fromExapascals(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "FeetOfSeaWater":
                unitOf=new UnitOf.Pressure().fromFeetOfSeaWater(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "FeetOfWater4C":
                unitOf=new UnitOf.Pressure().fromFeetOfWater4C(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "FeetOfWater60F":
                unitOf=new UnitOf.Pressure().fromFeetOfWater60F(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Femtopascals":
                unitOf=new UnitOf.Pressure().fromFemtopascals(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Gigapascals":
                unitOf=new UnitOf.Pressure().fromGigapascals(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "GramsPerSquareCentimeter":
                unitOf=new UnitOf.Pressure().fromGramsPerSquareCentimeter(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Hectopascals":
                unitOf=new UnitOf.Pressure().fromHectopascals(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "InchesOfMercury32F":
                unitOf=new UnitOf.Pressure().fromInchesOfMercury32F(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "InchesOfMercury60F":
                unitOf=new UnitOf.Pressure().fromInchesOfMercury60F(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "InchesOfWater4C":
                unitOf=new UnitOf.Pressure().fromInchesOfWater4C(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "InchesOfWater60F":
                unitOf=new UnitOf.Pressure().fromInchesOfWater60F(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "KSI":
                unitOf=new UnitOf.Pressure().fromKSI(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "KilogramsPerSquareCentimeter":
                unitOf=new UnitOf.Pressure().fromKilogramsPerSquareCentimeter(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "KilogramsPerSquareMeter":
                unitOf=new UnitOf.Pressure().fromKilogramsPerSquareMeter(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "KilogramsPerSquareMillimeter":
                unitOf=new UnitOf.Pressure().fromKilogramsPerSquareMillimeter(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "KilonewtonsPerSquareMeter":
                unitOf=new UnitOf.Pressure().fromKilonewtonsPerSquareMeter(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Kilopascals":
                unitOf=new UnitOf.Pressure().fromKilopascals(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "KipsPerSquareInch":
                unitOf=new UnitOf.Pressure().fromKipsPerSquareInch(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "LongTonsPerSquareFoot":
                unitOf=new UnitOf.Pressure().fromLongTonsPerSquareFoot(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "LongTonsPerSquareInch":
                unitOf=new UnitOf.Pressure().fromLongTonsPerSquareInch(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Megapascals":
                unitOf=new UnitOf.Pressure().fromMegapascals(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "MetersOfSeaWater":
                unitOf=new UnitOf.Pressure().fromMetersOfSeaWater(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "MetersOfWater4C":
                unitOf=new UnitOf.Pressure().fromMetersOfWater4C(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Microbars":
                unitOf=new UnitOf.Pressure().fromMicrobars(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Micropascals":
                unitOf=new UnitOf.Pressure().fromMicropascals(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Millibars":
                unitOf=new UnitOf.Pressure().fromMillibars(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "MillimetersOfMercury0C":
                unitOf=new UnitOf.Pressure().fromMillimetersOfMercury0C(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "MillimetersOfWater4C":
                unitOf=new UnitOf.Pressure().fromMillimetersOfWater4C(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Millipascals":
                unitOf=new UnitOf.Pressure().fromMillipascals(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Nanopascals":
                unitOf=new UnitOf.Pressure().fromNanopascals(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "NewtonsPerSquareCentimeter":
                unitOf=new UnitOf.Pressure().fromNewtonsPerSquareCentimeter(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "NewtonsPerSquareMeter":
                unitOf=new UnitOf.Pressure().fromNewtonsPerSquareMeter(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "NewtonsPerSquareMillimeter":
                unitOf=new UnitOf.Pressure().fromNewtonsPerSquareMillimeter(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "PSI":
                unitOf=new UnitOf.Pressure().fromPSI(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Pascals":
                unitOf=new UnitOf.Pressure().fromPascals(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Petapascals":
                unitOf=new UnitOf.Pressure().fromPetapascals(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Picopascals":
                unitOf=new UnitOf.Pressure().fromPicopascals(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Pieze":
                unitOf=new UnitOf.Pressure().fromPieze(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "PoundalsPerSquareFoot":
                unitOf=new UnitOf.Pressure().fromPoundalsPerSquareFoot(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "PoundsPerSquareFoot":
                unitOf=new UnitOf.Pressure().fromPoundsPerSquareFoot(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "PoundsPerSquareInch":
                unitOf=new UnitOf.Pressure().fromPoundsPerSquareInch(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "ShortTonsPerSquareFoot":
                unitOf=new UnitOf.Pressure().fromShortTonsPerSquareFoot(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "ShortTonsPerSquareInch":
                unitOf=new UnitOf.Pressure().fromShortTonsPerSquareInch(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "StandardAtmospheres":
                unitOf=new UnitOf.Pressure().fromStandardAtmospheres(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "SthenesPerSquareMeter":
                unitOf=new UnitOf.Pressure().fromSthenesPerSquareMeter(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "TechnicalAtmospheres":
                unitOf=new UnitOf.Pressure().fromTechnicalAtmospheres(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Terapascals":
                unitOf=new UnitOf.Pressure().fromTerapascals(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;
            case "Torrs":
                unitOf=new UnitOf.Pressure().fromTorrs(Double.valueOf(value));
                ans=PressureHelper(answer_unit,unitOf);
                break;

        }
        return ans;
    }

    private String SpeedHelper(String answer_unit,UnitOf.Speed unitOf){
        String ans="0";
        
        switch (answer_unit){

            case "CentimetersPerHour":
                ans=String.valueOf(unitOf.toCentimetersPerHour());
                break;
            case "CentimetersPerMinute":
                ans=String.valueOf(unitOf.toCentimetersPerMinute());
                break;
            case "CentimetersPerSecond":
                ans=String.valueOf(unitOf.toCentimetersPerSecond());
                break;
            case "EarthsVelocity":
                ans=String.valueOf(unitOf.toEarthsVelocity());
                break;
            case "FeetPerHour":
                ans=String.valueOf(unitOf.toFeetPerHour());
                break;
            case "FeetPerMinute":
                ans=String.valueOf(unitOf.toFeetPerMinute());
                break;
            case "FeetPerSecond":
                ans=String.valueOf(unitOf.toFeetPerSecond());
                break;
            case "FirstCosmicVelocity":
                ans=String.valueOf(unitOf.toFirstCosmicVelocity());
                break;
            case "InchesPerHour":
                ans=String.valueOf(unitOf.toInchesPerHour());
                break;
            case "InchesPerMinute":
                ans=String.valueOf(unitOf.toInchesPerMinute());
                break;
            case "InchesPerSecond":
                ans=String.valueOf(unitOf.toInchesPerSecond());
                break;
            case "KilometersPerHour":
                ans=String.valueOf(unitOf.toKilometersPerHour());
                break;
            case "KilometersPerMinute":
                ans=String.valueOf(unitOf.toKilometersPerMinute());
                break;
            case "KilometersPerSecond":
                ans=String.valueOf(unitOf.toKilometersPerSecond());
                break;
            case "Knots":
                ans=String.valueOf(unitOf.toKnots());
                break;
            case "Light":
                ans=String.valueOf(unitOf.toLight());
                break;
            case "Mach":
                ans=String.valueOf(unitOf.toMach());
                break;
            case "MetersPerHour":
                ans=String.valueOf(unitOf.toMetersPerHour());
                break;
            case "MetersPerMinute":
                ans=String.valueOf(unitOf.toMetersPerMinute());
                break;
            case "MetersPerSecond":
                ans=String.valueOf(unitOf.toMetersPerSecond());
                break;
            case "MilesPerHour":
                ans=String.valueOf(unitOf.toMilesPerHour());
                break;
            case "MilesPerMinute":
                ans=String.valueOf(unitOf.toMilesPerMinute());
                break;
            case "MilesPerSecond":
                ans=String.valueOf(unitOf.toMilesPerSecond());
                break;
            case "MillimetersPerHour":
                ans=String.valueOf(unitOf.toMillimetersPerHour());
                break;
            case "MillimetersPerMinute":
                ans=String.valueOf(unitOf.toMillimetersPerMinute());
                break;
            case "MillimetersPerSecond":
                ans=String.valueOf(unitOf.toMillimetersPerSecond());
                break;
            case "SecondCosmicVelocity":
                ans=String.valueOf(unitOf.toSecondCosmicVelocity());
                break;
            case "SoundsInAir":
                ans=String.valueOf(unitOf.toSoundsInAir());
                break;
            case "SoundsInWater":
                ans=String.valueOf(unitOf.toSoundsInWater());
                break;
            case "ThirdCosmicVelocity":
                ans=String.valueOf(unitOf.toThirdCosmicVelocity());
                break;
            case "YardsPerHour":
                ans=String.valueOf(unitOf.toYardsPerHour());
                break;
            case "YardsPerMinute":
                ans=String.valueOf(unitOf.toYardsPerMinute());
                break;
            case "YardsPerSecond":
                ans=String.valueOf(unitOf.toYardsPerSecond());
                break;

        }
        return ans;
    }

    private String Speed(String value,String value_unit,String answer_unit){
        String ans="0";
        UnitOf.Speed unitOf;
        
        switch (value_unit){

            case "CentimetersPerHour":
                unitOf=new UnitOf.Speed().fromCentimetersPerHour(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "CentimetersPerMinute":
                unitOf=new UnitOf.Speed().fromCentimetersPerMinute(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "CentimetersPerSecond":
                unitOf=new UnitOf.Speed().fromCentimetersPerSecond(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "EarthsVelocity":
                unitOf=new UnitOf.Speed().fromEarthsVelocity(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "FeetPerHour":
                unitOf=new UnitOf.Speed().fromFeetPerHour(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "FeetPerMinute":
                unitOf=new UnitOf.Speed().fromFeetPerMinute(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "FeetPerSecond":
                unitOf=new UnitOf.Speed().fromFeetPerSecond(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "FirstCosmicVelocity":
                unitOf=new UnitOf.Speed().fromFirstCosmicVelocity(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "InchesPerHour":
                unitOf=new UnitOf.Speed().fromInchesPerHour(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "InchesPerMinute":
                unitOf=new UnitOf.Speed().fromInchesPerMinute(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "InchesPerSecond":
                unitOf=new UnitOf.Speed().fromInchesPerSecond(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "KilometersPerHour":
                unitOf=new UnitOf.Speed().fromKilometersPerHour(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "KilometersPerMinute":
                unitOf=new UnitOf.Speed().fromKilometersPerMinute(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "KilometersPerSecond":
                unitOf=new UnitOf.Speed().fromKilometersPerSecond(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "Knots":
                unitOf=new UnitOf.Speed().fromKnots(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "Light":
                unitOf=new UnitOf.Speed().fromLight(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "Mach":
                unitOf=new UnitOf.Speed().fromMach(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "MetersPerHour":
                unitOf=new UnitOf.Speed().fromMetersPerHour(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "MetersPerMinute":
                unitOf=new UnitOf.Speed().fromMetersPerMinute(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "MetersPerSecond":
                unitOf=new UnitOf.Speed().fromMetersPerSecond(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "MilesPerHour":
                unitOf=new UnitOf.Speed().fromMilesPerHour(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "MilesPerMinute":
                unitOf=new UnitOf.Speed().fromMilesPerMinute(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "MilesPerSecond":
                unitOf=new UnitOf.Speed().fromMilesPerSecond(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "MillimetersPerHour":
                unitOf=new UnitOf.Speed().fromMillimetersPerHour(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "MillimetersPerMinute":
                unitOf=new UnitOf.Speed().fromMillimetersPerMinute(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "MillimetersPerSecond":
                unitOf=new UnitOf.Speed().fromMillimetersPerSecond(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "SecondCosmicVelocity":
                unitOf=new UnitOf.Speed().fromSecondCosmicVelocity(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "SoundsInAir":
                unitOf=new UnitOf.Speed().fromSoundsInAir(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "SoundsInWater":
                unitOf=new UnitOf.Speed().fromSoundsInWater(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "ThirdCosmicVelocity":
                unitOf=new UnitOf.Speed().fromThirdCosmicVelocity(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "YardsPerHour":
                unitOf=new UnitOf.Speed().fromYardsPerHour(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "YardsPerMinute":
                unitOf=new UnitOf.Speed().fromYardsPerMinute(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;
            case "YardsPerSecond":
                unitOf=new UnitOf.Speed().fromYardsPerSecond(Double.valueOf(value));
                ans=SpeedHelper(answer_unit,unitOf);
                break;

        }
        return ans;
    }

    private String TimeHelper(String answer_unit,UnitOf.Time unitOf){
        String ans="0";
        
        switch (answer_unit){

            case "Attoseconds":
                ans=String.valueOf(unitOf.toAttoseconds());
                break;
            case "Centuries":
                ans=String.valueOf(unitOf.toCenturies());
                break;
            case "Days":
                ans=String.valueOf(unitOf.toDays());
                break;
            case "Decades":
                ans=String.valueOf(unitOf.toDecades());
                break;
            case "Femtoseconds":
                ans=String.valueOf(unitOf.toFemtoseconds());
                break;
            case "Fortnights":
                ans=String.valueOf(unitOf.toFortnights());
                break;
            case "GregorianYears":
                ans=String.valueOf(unitOf.toGregorianYears());
                break;
            case "Hours":
                ans=String.valueOf(unitOf.toHours());
                break;
            case "JulianYears":
                ans=String.valueOf(unitOf.toJulianYears());
                break;
            case "LeapYears":
                ans=String.valueOf(unitOf.toLeapYears());
                break;
            case "Microseconds":
                ans=String.valueOf(unitOf.toMicroseconds());
                break;
            case "Millenniums":
                ans=String.valueOf(unitOf.toMillenniums());
                break;
            case "Milliseconds":
                ans=String.valueOf(unitOf.toMilliseconds());
                break;
            case "Minutes":
                ans=String.valueOf(unitOf.toMinutes());
                break;
            case "Months":
                ans=String.valueOf(unitOf.toMonths());
                break;
            case "Nanoseconds":
                ans=String.valueOf(unitOf.toNanoseconds());
                break;
            case "Picoseconds":
                ans=String.valueOf(unitOf.toPicoseconds());
                break;
            case "Seconds":
                ans=String.valueOf(unitOf.toSeconds());
                break;
            case "Weeks":
                ans=String.valueOf(unitOf.toWeeks());
                break;
            case "Years":
                ans=String.valueOf(unitOf.toYears());
                break;

        }
        return ans;
    }

    private String Time(String value,String value_unit,String answer_unit){
        String ans="0";
        UnitOf.Time unitOf;

        switch (value_unit){

            case "Attoseconds":
                unitOf=new UnitOf.Time().fromAttoseconds(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;
            case "Centuries":
                unitOf=new UnitOf.Time().fromCenturies(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;
            case "Days":
                unitOf=new UnitOf.Time().fromDays(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;
            case "Decades":
                unitOf=new UnitOf.Time().fromDecades(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;
            case "Femtoseconds":
                unitOf=new UnitOf.Time().fromFemtoseconds(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;
            case "Fortnights":
                unitOf=new UnitOf.Time().fromFortnights(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;
            case "GregorianYears":
                unitOf=new UnitOf.Time().fromGregorianYears(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;
            case "Hours":
                unitOf=new UnitOf.Time().fromHours(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;
            case "JulianYears":
                unitOf=new UnitOf.Time().fromJulianYears(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;
            case "LeapYears":
                unitOf=new UnitOf.Time().fromLeapYears(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;
            case "Microseconds":
                unitOf=new UnitOf.Time().fromMicroseconds(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;
            case "Millenniums":
                unitOf=new UnitOf.Time().fromMillenniums(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;
            case "Milliseconds":
                unitOf=new UnitOf.Time().fromMilliseconds(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;
            case "Minutes":
                unitOf=new UnitOf.Time().fromMinutes(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;
            case "Months":
                unitOf=new UnitOf.Time().fromMonths(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;
            case "Nanoseconds":
                unitOf=new UnitOf.Time().fromNanoseconds(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;
            case "Picoseconds":
                unitOf=new UnitOf.Time().fromPicoseconds(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;
            case "Seconds":
                unitOf=new UnitOf.Time().fromSeconds(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;
            case "Weeks":
                unitOf=new UnitOf.Time().fromWeeks(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;
            case "Years":
                unitOf=new UnitOf.Time().fromYears(Double.valueOf(value));
                ans=TimeHelper(answer_unit,unitOf);
                break;

        }
        return ans;
    }

    private String TorqueHelper(String answer_unit,UnitOf.Torque unitOf){
        String ans="0";
        
        switch (answer_unit){

            case"DyneCentimeters":
                ans=String.valueOf(unitOf.toDyneCentimeters());
                break;
            case"DyneMeters":
                ans=String.valueOf(unitOf.toDyneMeters());
                break;
            case"DyneMillimeters":
                ans=String.valueOf(unitOf.toDyneMillimeters());
                break;
            case"GramCentimeters":
                ans=String.valueOf(unitOf.toGramCentimeters());
                break;
            case"GramMeters":
                ans=String.valueOf(unitOf.toGramMeters());
                break;
            case"GramMillimeters":
                ans=String.valueOf(unitOf.toGramMillimeters());
                break;
            case"KilogramCentimeters":
                ans=String.valueOf(unitOf.toKilogramCentimeters());
                break;
            case"KilogramMeters":
                ans=String.valueOf(unitOf.toKilogramMeters());
                break;
            case"KilogramMillimeters":
                ans=String.valueOf(unitOf.toKilogramMillimeters());
                break;
            case"KilonewtonMeters":
                ans=String.valueOf(unitOf.toKilonewtonMeters());
                break;
            case"NewtonCentimeters":
                ans=String.valueOf(unitOf.toNewtonCentimeters());
                break;
            case"NewtonMeters":
                ans=String.valueOf(unitOf.toNewtonMeters());
                break;
            case"NewtonMillimeters":
                ans=String.valueOf(unitOf.toNewtonMillimeters());
                break;
            case"OunceFeet":
                ans=String.valueOf(unitOf.toOunceFeet());
                break;
            case"OunceInches":
                ans=String.valueOf(unitOf.toOunceInches());
                break;
            case"PoundFeet":
                ans=String.valueOf(unitOf.toPoundFeet());
                break;
            case"PoundInches":
                ans=String.valueOf(unitOf.toPoundInches());
                break;

        }
        return ans;
    }

    private String Torque(String value,String value_unit,String answer_unit){
        String ans="0";
        UnitOf.Torque unitOf;
        
        switch (value_unit){

            case "DyneCentimeters":
                unitOf=new UnitOf.Torque().fromDyneCentimeters(Double.valueOf(value));
                ans=TorqueHelper(answer_unit,unitOf);
                break;
            case "DyneMeters":
                unitOf=new UnitOf.Torque().fromDyneMeters(Double.valueOf(value));
                ans=TorqueHelper(answer_unit,unitOf);
                break;
            case "DyneMillimeters":
                unitOf=new UnitOf.Torque().fromDyneMillimeters(Double.valueOf(value));
                ans=TorqueHelper(answer_unit,unitOf);
                break;
            case "GramCentimeters":
                unitOf=new UnitOf.Torque().fromGramCentimeters(Double.valueOf(value));
                ans=TorqueHelper(answer_unit,unitOf);
                break;
            case "GramMeters":
                unitOf=new UnitOf.Torque().fromGramMeters(Double.valueOf(value));
                ans=TorqueHelper(answer_unit,unitOf);
                break;
            case "GramMillimeters":
                unitOf=new UnitOf.Torque().fromGramMillimeters(Double.valueOf(value));
                ans=TorqueHelper(answer_unit,unitOf);
                break;
            case "KilogramCentimeters":
                unitOf=new UnitOf.Torque().fromKilogramCentimeters(Double.valueOf(value));
                ans=TorqueHelper(answer_unit,unitOf);
                break;
            case "KilogramMeters":
                unitOf=new UnitOf.Torque().fromKilogramMeters(Double.valueOf(value));
                ans=TorqueHelper(answer_unit,unitOf);
                break;
            case "KilogramMillimeters":
                unitOf=new UnitOf.Torque().fromKilogramMillimeters(Double.valueOf(value));
                ans=TorqueHelper(answer_unit,unitOf);
                break;
            case "KilonewtonMeters":
                unitOf=new UnitOf.Torque().fromKilonewtonMeters(Double.valueOf(value));
                ans=TorqueHelper(answer_unit,unitOf);
                break;
            case "NewtonCentimeters":
                unitOf=new UnitOf.Torque().fromNewtonCentimeters(Double.valueOf(value));
                ans=TorqueHelper(answer_unit,unitOf);
                break;
            case "NewtonMeters":
                unitOf=new UnitOf.Torque().fromNewtonMeters(Double.valueOf(value));
                ans=TorqueHelper(answer_unit,unitOf);
                break;
            case "NewtonMillimeters":
                unitOf=new UnitOf.Torque().fromNewtonMillimeters(Double.valueOf(value));
                ans=TorqueHelper(answer_unit,unitOf);
                break;
            case "OunceFeet":
                unitOf=new UnitOf.Torque().fromOunceFeet(Double.valueOf(value));
                ans=TorqueHelper(answer_unit,unitOf);
                break;
            case "OunceInches":
                unitOf=new UnitOf.Torque().fromOunceInches(Double.valueOf(value));
                ans=TorqueHelper(answer_unit,unitOf);
                break;
            case "PoundFeet":
                unitOf=new UnitOf.Torque().fromPoundFeet(Double.valueOf(value));
                ans=TorqueHelper(answer_unit,unitOf);
                break;
            case "PoundInches":
                unitOf=new UnitOf.Torque().fromPoundInches(Double.valueOf(value));
                ans=TorqueHelper(answer_unit,unitOf);
                break;

        }
        return ans;
    }

    private String VolumeHelper(String answer_unit,UnitOf.Volume unitOf){
        String ans="0";
        
        switch (answer_unit){

            case "AcreFeetUSSurvey":
                ans=String.valueOf(unitOf.toAcreFeetUSSurvey());
                break;
            case "AcreInches":
                ans=String.valueOf(unitOf.toAcreInches());
                break;
            case "ArceFeet":
                ans=String.valueOf(unitOf.toArceFeet());
                break;
            case "Attoliters":
                ans=String.valueOf(unitOf.toAttoliters());
                break;
            case "BarrelsOfOil":
                ans=String.valueOf(unitOf.toBarrelsOfOil());
                break;
            case "bbl":
                ans=String.valueOf(unitOf.toBarrelsUK());
                break;
            case "BarrelsUS":
                ans=String.valueOf(unitOf.toBarrelsUS());
                break;
            case "BoardFeet":
                ans=String.valueOf(unitOf.toBoardFeet());
                break;
            case "Centiliters":
                ans=String.valueOf(unitOf.toCentiliters());
                break;
            case "Cords":
                ans=String.valueOf(unitOf.toCords());
                break;
            case "cm3":
                ans=String.valueOf(unitOf.toCubicCentimeters());
                break;
            case "CubicDecimeters":
                ans=String.valueOf(unitOf.toCubicDecimeters());
                break;
            case "ft3":
                ans=String.valueOf(unitOf.toCubicFeet());
                break;
            case "in3":
                ans=String.valueOf(unitOf.toCubicInches());
                break;
            case "CubicKilometers":
                ans=String.valueOf(unitOf.toCubicKilometers());
                break;
            case "m3":
                ans=String.valueOf(unitOf.toCubicMeters());
                break;
            case "CubicMiles":
                ans=String.valueOf(unitOf.toCubicMiles());
                break;
            case "CubicMillimeters":
                ans=String.valueOf(unitOf.toCubicMillimeters());
                break;
            case "yard3":
                ans=String.valueOf(unitOf.toCubicYards());
                break;
            case "CupsMetric":
                ans=String.valueOf(unitOf.toCupsMetric());
                break;
            case "CupsUK":
                ans=String.valueOf(unitOf.toCupsUK());
                break;
            case "CupsUS":
                ans=String.valueOf(unitOf.toCupsUS());
                break;
            case "Deciliters":
                ans=String.valueOf(unitOf.toDeciliters());
                break;
            case "Decisteres":
                ans=String.valueOf(unitOf.toDecisteres());
                break;
            case "Dekaliters":
                ans=String.valueOf(unitOf.toDekaliters());
                break;
            case "Dekasteres":
                ans=String.valueOf(unitOf.toDekasteres());
                break;
            case "DessertspoonsUK":
                ans=String.valueOf(unitOf.toDessertspoonsUK());
                break;
            case "DessertspoonsUS":
                ans=String.valueOf(unitOf.toDessertspoonsUS());
                break;
            case "Drops":
                ans=String.valueOf(unitOf.toDrops());
                break;
            case "Exaliters":
                ans=String.valueOf(unitOf.toExaliters());
                break;
            case "Femtoliters":
                ans=String.valueOf(unitOf.toFemtoliters());
                break;
            case "FluidOuncesUK":
                ans=String.valueOf(unitOf.toFluidOuncesUK());
                break;
            case "fl.oz":
                ans=String.valueOf(unitOf.toFluidOuncesUS());
                break;
            case "gal IM":
                ans=String.valueOf(unitOf.toGallonsUK());
                break;
            case "gal US":
                ans=String.valueOf(unitOf.toGallonsUS());
                break;
            case "Gigaliters":
                ans=String.valueOf(unitOf.toGigaliters());
                break;
            case "GillsUK":
                ans=String.valueOf(unitOf.toGillsUK());
                break;
            case "GillsUS":
                ans=String.valueOf(unitOf.toGillsUS());
                break;
            case "Hectoliters":
                ans=String.valueOf(unitOf.toHectoliters());
                break;
            case "Hogsheads":
                ans=String.valueOf(unitOf.toHogsheads());
                break;
            case "HundredCubicFeet":
                ans=String.valueOf(unitOf.toHundredCubicFeet());
                break;
            case "Kiloliters":
                ans=String.valueOf(unitOf.toKiloliters());
                break;
            case "lit":
                ans=String.valueOf(unitOf.toLiters());
                break;
            case "Megaliters":
                ans=String.valueOf(unitOf.toMegaliters());
                break;
            case "Microliters":
                ans=String.valueOf(unitOf.toMicroliters());
                break;
            case "ml":
                ans=String.valueOf(unitOf.toMilliliters());
                break;
            case "MinimsUK":
                ans=String.valueOf(unitOf.toMinimsUK());
                break;
            case "MinimsUS":
                ans=String.valueOf(unitOf.toMinimsUS());
                break;
            case "Nanoliters":
                ans=String.valueOf(unitOf.toNanoliters());
                break;
            case "Petaliters":
                ans=String.valueOf(unitOf.toPetaliters());
                break;
            case "Picoliters":
                ans=String.valueOf(unitOf.toPicoliters());
                break;
            case "PintsUK":
                ans=String.valueOf(unitOf.toPintsUK());
                break;
            case "PintsUS":
                ans=String.valueOf(unitOf.toPintsUS());
                break;
            case "QuartsUK":
                ans=String.valueOf(unitOf.toQuartsUK());
                break;
            case "QuartsUS":
                ans=String.valueOf(unitOf.toQuartsUS());
                break;
            case "RegisterTons":
                ans=String.valueOf(unitOf.toRegisterTons());
                break;
            case "Steres":
                ans=String.valueOf(unitOf.toSteres());
                break;
            case "TablespoonsMetric":
                ans=String.valueOf(unitOf.toTablespoonsMetric());
                break;
            case "TablespoonsUK":
                ans=String.valueOf(unitOf.toTablespoonsUK());
                break;
            case "TablespoonsUS":
                ans=String.valueOf(unitOf.toTablespoonsUS());
                break;
            case "TeaspoonsMetric":
                ans=String.valueOf(unitOf.toTeaspoonsMetric());
                break;
            case "TeaspoonsUK":
                ans=String.valueOf(unitOf.toTeaspoonsUK());
                break;
            case "TeaspoonsUS":
                ans=String.valueOf(unitOf.toTeaspoonsUS());
                break;
            case "Teraliters":
                ans=String.valueOf(unitOf.toTeraliters());
                break;
            case "Tuns":
                ans=String.valueOf(unitOf.toTuns());
                break;

        }
        return ans;
    }

    private String Volume(String value,String value_unit,String answer_unit){
        String ans="0";
        UnitOf.Volume unitOf;
        
        switch (value_unit){

            case "AcreFeetUSSurvey":
                unitOf=new UnitOf.Volume().fromAcreFeetUSSurvey(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "AcreInches":
                unitOf=new UnitOf.Volume().fromAcreInches(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "ArceFeet":
                unitOf=new UnitOf.Volume().fromArceFeet(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Attoliters":
                unitOf=new UnitOf.Volume().fromAttoliters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "BarrelsOfOil":
                unitOf=new UnitOf.Volume().fromBarrelsOfOil(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "bbl":
                unitOf=new UnitOf.Volume().fromBarrelsUK(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "BarrelsUS":
                unitOf=new UnitOf.Volume().fromBarrelsUS(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "BoardFeet":
                unitOf=new UnitOf.Volume().fromBoardFeet(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Centiliters":
                unitOf=new UnitOf.Volume().fromCentiliters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Cords":
                unitOf=new UnitOf.Volume().fromCords(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "cm3":
                unitOf=new UnitOf.Volume().fromCubicCentimeters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "CubicDecimeters":
                unitOf=new UnitOf.Volume().fromCubicDecimeters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "ft3":
                unitOf=new UnitOf.Volume().fromCubicFeet(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "in3":
                unitOf=new UnitOf.Volume().fromCubicInches(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "CubicKilometers":
                unitOf=new UnitOf.Volume().fromCubicKilometers(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "m3":
                unitOf=new UnitOf.Volume().fromCubicMeters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "CubicMiles":
                unitOf=new UnitOf.Volume().fromCubicMiles(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "CubicMillimeters":
                unitOf=new UnitOf.Volume().fromCubicMillimeters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "yard3":
                unitOf=new UnitOf.Volume().fromCubicYards(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "CupsMetric":
                unitOf=new UnitOf.Volume().fromCupsMetric(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "CupsUK":
                unitOf=new UnitOf.Volume().fromCupsUK(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "CupsUS":
                unitOf=new UnitOf.Volume().fromCupsUS(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Deciliters":
                unitOf=new UnitOf.Volume().fromDeciliters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Decisteres":
                unitOf=new UnitOf.Volume().fromDecisteres(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Dekaliters":
                unitOf=new UnitOf.Volume().fromDekaliters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Dekasteres":
                unitOf=new UnitOf.Volume().fromDekasteres(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "DessertspoonsUK":
                unitOf=new UnitOf.Volume().fromDessertspoonsUK(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "DessertspoonsUS":
                unitOf=new UnitOf.Volume().fromDessertspoonsUS(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Drops":
                unitOf=new UnitOf.Volume().fromDrops(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Exaliters":
                unitOf=new UnitOf.Volume().fromExaliters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Femtoliters":
                unitOf=new UnitOf.Volume().fromFemtoliters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "FluidOuncesUK":
                unitOf=new UnitOf.Volume().fromFluidOuncesUK(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "fl.oz":
                unitOf=new UnitOf.Volume().fromFluidOuncesUS(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "gal IM":
                unitOf=new UnitOf.Volume().fromGallonsUK(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "gal US":
                unitOf=new UnitOf.Volume().fromGallonsUS(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Gigaliters":
                unitOf=new UnitOf.Volume().fromGigaliters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "GillsUK":
                unitOf=new UnitOf.Volume().fromGillsUK(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "GillsUS":
                unitOf=new UnitOf.Volume().fromGillsUS(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Hectoliters":
                unitOf=new UnitOf.Volume().fromHectoliters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Hogsheads":
                unitOf=new UnitOf.Volume().fromHogsheads(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "HundredCubicFeet":
                unitOf=new UnitOf.Volume().fromHundredCubicFeet(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Kiloliters":
                unitOf=new UnitOf.Volume().fromKiloliters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "lit":
                unitOf=new UnitOf.Volume().fromLiters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Megaliters":
                unitOf=new UnitOf.Volume().fromMegaliters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Microliters":
                unitOf=new UnitOf.Volume().fromMicroliters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "ml":
                unitOf=new UnitOf.Volume().fromMilliliters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "MinimsUK":
                unitOf=new UnitOf.Volume().fromMinimsUK(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "MinimsUS":
                unitOf=new UnitOf.Volume().fromMinimsUS(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Nanoliters":
                unitOf=new UnitOf.Volume().fromNanoliters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Petaliters":
                unitOf=new UnitOf.Volume().fromPetaliters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Picoliters":
                unitOf=new UnitOf.Volume().fromPicoliters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "PintsUK":
                unitOf=new UnitOf.Volume().fromPintsUK(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "PintsUS":
                unitOf=new UnitOf.Volume().fromPintsUS(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "QuartsUK":
                unitOf=new UnitOf.Volume().fromQuartsUK(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "QuartsUS":
                unitOf=new UnitOf.Volume().fromQuartsUS(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "RegisterTons":
                unitOf=new UnitOf.Volume().fromRegisterTons(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Steres":
                unitOf=new UnitOf.Volume().fromSteres(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "TablespoonsMetric":
                unitOf=new UnitOf.Volume().fromTablespoonsMetric(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "TablespoonsUK":
                unitOf=new UnitOf.Volume().fromTablespoonsUK(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "TablespoonsUS":
                unitOf=new UnitOf.Volume().fromTablespoonsUS(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "TeaspoonsMetric":
                unitOf=new UnitOf.Volume().fromTeaspoonsMetric(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "TeaspoonsUK":
                unitOf=new UnitOf.Volume().fromTeaspoonsUK(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "TeaspoonsUS":
                unitOf=new UnitOf.Volume().fromTeaspoonsUS(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Teraliters":
                unitOf=new UnitOf.Volume().fromTeraliters(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;
            case "Tuns":
                unitOf=new UnitOf.Volume().fromTuns(Double.valueOf(value));
                ans=VolumeHelper(answer_unit,unitOf);
                break;

        }
        return ans;
    }

    private String ElectircHelper(String answer_unit,UnitOf.ElectricCharge unitOf){
        String ans="0";
        
        switch (answer_unit){

            case "Abcoulombs":
                ans=String.valueOf(unitOf.toAbcoulombs());
                break;
            case "AmpereHours":
                ans=String.valueOf(unitOf.toAmpereHours());
                break;
            case "AmpereMinutes":
                ans=String.valueOf(unitOf.toAmpereMinutes());
                break;
            case "AmpereSeconds":
                ans=String.valueOf(unitOf.toAmpereSeconds());
                break;
            case "Coulombs":
                ans=String.valueOf(unitOf.toCoulombs());
                break;
            case "EMUsOfCharge":
                ans=String.valueOf(unitOf.toEMUsOfCharge());
                break;
            case "ESUsOfCharge":
                ans=String.valueOf(unitOf.toESUsOfCharge());
                break;
            case "ElectronCharge":
                ans=String.valueOf(unitOf.toElectronCharge());
                break;
            case "FaradVolts":
                ans=String.valueOf(unitOf.toFaradVolts());
                break;
            case "FaradayCarbon12":
                ans=String.valueOf(unitOf.toFaradayCarbon12());
                break;
            case "FaradayChemistry":
                ans=String.valueOf(unitOf.toFaradayChemistry());
                break;
            case "FaradayPhysics":
                ans=String.valueOf(unitOf.toFaradayPhysics());
                break;
            case "Franklins":
                ans=String.valueOf(unitOf.toFranklins());
                break;
            case "Kilocoulombs":
                ans=String.valueOf(unitOf.toKilocoulombs());
                break;
            case "Megacoulombs":
                ans=String.valueOf(unitOf.toMegacoulombs());
                break;
            case "Microcoulombs":
                ans=String.valueOf(unitOf.toMicrocoulombs());
                break;
            case "Millicoulombs":
                ans=String.valueOf(unitOf.toMillicoulombs());
                break;
            case "Nanocoulombs":
                ans=String.valueOf(unitOf.toNanocoulombs());
                break;
            case "Picocoulombs":
                ans=String.valueOf(unitOf.toPicocoulombs());
                break;
            case "Statcoulombs":
                ans=String.valueOf(unitOf.toStatcoulombs());
                break;

        }
        return ans;
    }

    private String Electric(String value,String value_unit,String answer_unit){
        String ans="0";
        UnitOf.ElectricCharge unitOf;
        
        switch (value_unit){

            case "Abcoulombs":
                unitOf=new UnitOf.ElectricCharge().fromAbcoulombs(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;
            case "AmpereHours":
                unitOf=new UnitOf.ElectricCharge().fromAmpereHours(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;
            case "AmpereMinutes":
                unitOf=new UnitOf.ElectricCharge().fromAmpereMinutes(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;
            case "AmpereSeconds":
                unitOf=new UnitOf.ElectricCharge().fromAmpereSeconds(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;
            case "Coulombs":
                unitOf=new UnitOf.ElectricCharge().fromCoulombs(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;
            case "EMUsOfCharge":
                unitOf=new UnitOf.ElectricCharge().fromEMUsOfCharge(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;
            case "ESUsOfCharge":
                unitOf=new UnitOf.ElectricCharge().fromESUsOfCharge(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;
            case "ElectronCharge":
                unitOf=new UnitOf.ElectricCharge().fromElectronCharge(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;
            case "FaradVolts":
                unitOf=new UnitOf.ElectricCharge().fromFaradVolts(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;
            case "FaradayCarbon12":
                unitOf=new UnitOf.ElectricCharge().fromFaradayCarbon12(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;
            case "FaradayChemistry":
                unitOf=new UnitOf.ElectricCharge().fromFaradayChemistry(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;
            case "FaradayPhysics":
                unitOf=new UnitOf.ElectricCharge().fromFaradayPhysics(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;
            case "Franklins":
                unitOf=new UnitOf.ElectricCharge().fromFranklins(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;
            case "Kilocoulombs":
                unitOf=new UnitOf.ElectricCharge().fromKilocoulombs(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;
            case "Megacoulombs":
                unitOf=new UnitOf.ElectricCharge().fromMegacoulombs(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;
            case "Microcoulombs":
                unitOf=new UnitOf.ElectricCharge().fromMicrocoulombs(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;
            case "Millicoulombs":
                unitOf=new UnitOf.ElectricCharge().fromMillicoulombs(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;
            case "Nanocoulombs":
                unitOf=new UnitOf.ElectricCharge().fromNanocoulombs(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;
            case "Picocoulombs":
                unitOf=new UnitOf.ElectricCharge().fromPicocoulombs(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;
            case "Statcoulombs":
                unitOf=new UnitOf.ElectricCharge().fromStatcoulombs(Double.valueOf(value));
                ans=ElectircHelper(answer_unit,unitOf);
                break;

        }
        return ans;
    }

    private String TemparetureHelper(String answer_unit,UnitOf.Temperature unitOf){
        String ans="0";
        switch (answer_unit){

            case "Celsius":
                ans=String.valueOf(unitOf.toCelsius());
                break;
            case "Fahrenheit":
                ans=String.valueOf(unitOf.toFahrenheit());
                break;
            case "Kelvin":
                ans=String.valueOf(unitOf.toKelvin());
                break;
            case "Rankine":
                ans=String.valueOf(unitOf.toRankine());
                break;
            case "Reaumur":
                ans=String.valueOf(unitOf.toReaumur());
                break;

        }
        return ans;
    }

    private String Temperature(String value,String value_unit,String answer_unit){
        String ans="0";
        UnitOf.Temperature unitOf;

        switch (value_unit){

            case "Celsius":
                unitOf=new UnitOf.Temperature().fromCelsius(Double.valueOf(value));
                ans=TemparetureHelper(answer_unit,unitOf);
                break;
            case "Fahrenheit":
                unitOf=new UnitOf.Temperature().fromFahrenheit(Double.valueOf(value));
                ans=TemparetureHelper(answer_unit,unitOf);
                break;
            case "Kelvin":
                unitOf=new UnitOf.Temperature().fromKelvin(Double.valueOf(value));
                ans=TemparetureHelper(answer_unit,unitOf);
                break;
            case "Rankine":
                unitOf=new UnitOf.Temperature().fromRankine(Double.valueOf(value));
                ans=TemparetureHelper(answer_unit,unitOf);
                break;
            case "Reaumur":
                unitOf=new UnitOf.Temperature().fromReaumur(Double.valueOf(value));
                ans=TemparetureHelper(answer_unit,unitOf);
                break;
        }
        return ans;
    }

    private String NumericHelper(String answer_unit,UnitOf.NumericBase unitOf){
        String ans="0";

        try {
            switch (answer_unit) {

                case "Binary":
                    ans = String.valueOf(unitOf.toBinary());
                    break;
                case "Decimal":
                    ans = String.valueOf(unitOf.toDecimal());
                    break;
                case "Hexadecimal":
                    ans = String.valueOf(unitOf.toHexadecimal());
                    break;
                case "Octal":
                    ans = String.valueOf(unitOf.toOctal());
                    break;

            }
        }catch (Exception e){}
        return ans;
    }

    private String Numeric(String value,String value_unit,String answer_unit){
        String ans="0";
        UnitOf.NumericBase unitOf;
        
        switch (value_unit){

            case "Binary":
                unitOf=new UnitOf.NumericBase().fromBinary(Double.valueOf(value));
                ans=NumericHelper(answer_unit,unitOf);
                break;
            case "Decimal":
                unitOf=new UnitOf.NumericBase().fromDecimal(Double.valueOf(value));
                ans=NumericHelper(answer_unit,unitOf);
                break;
            case "Hexadecimal":
                unitOf=new UnitOf.NumericBase().fromHexadecimal(Double.valueOf(value));
                ans=NumericHelper(answer_unit,unitOf);
                break;
            case "Octal":
                unitOf=new UnitOf.NumericBase().fromOctal(Double.valueOf(value));
                ans=NumericHelper(answer_unit,unitOf);
                break;
        }
        return ans;
    }
    
}
