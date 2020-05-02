package ir.tdaapp.paymanyar.Model.Utilitys;

import com.digidemic.unitof.UnitOf;

import java.util.ArrayList;

public class UnitConversion {

    public ArrayList<String> ShowSpinnerValues(int mode){
        ArrayList<String> answer=new ArrayList<>();

        switch (mode){

            // Acceleration
            case 1:
                answer.add("AttometersPerSecondSquared");
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
                answer.add("Ares");
                answer.add("Arpents");
                answer.add("Barns");
                answer.add("CircularInches");
                answer.add("CircularMils");
                answer.add("Hectares");
                answer.add("Homesteads");
                answer.add("Roods");
                answer.add("Sabins");
                answer.add("SquareCentimeters");
                answer.add("SquareChains");
                answer.add("SquareDecimeters");
                answer.add("SquareDekameters");
                answer.add("SquareFeet");
                answer.add("SquareHectometers");
                answer.add("SquareInches");
                answer.add("SquareKilometers");
                answer.add("SquareMeters");
                answer.add("SquareMicrometers");
                answer.add("SquareMiles");
                answer.add("SquareMillimeters");
                answer.add("SquareNanometers");
                answer.add("SquarePerches");
                answer.add("SquarePoles");
                answer.add("SquareRods");
                answer.add("SquareYards");
                break;

            // Denisty
            case 4:
                answer.add("");

                break;

            // Distance
            case 5:
                answer.add("Attometers");
                answer.add("Barleycorns");
                answer.add("CablesImperial");
                answer.add("CablesInternational");
                answer.add("CablesUSCustomary");
                answer.add("Caliber");
                answer.add("Centiinches");
                answer.add("Centimeters");
                answer.add("Chains");
                answer.add("Cubits");
                answer.add("Decimeters");
                answer.add("Dekameters");
                answer.add("Ells");
                answer.add("Exameters");
                answer.add("Fathoms");
                answer.add("Feet");
                answer.add("Femtometers");
                answer.add("Fingers");
                answer.add("Furlongs");
                answer.add("Gigameters");
                answer.add("Hands");
                answer.add("Hectometers");
                answer.add("Inches");
                answer.add("Kilometers");
                answer.add("Kiloyards");
                answer.add("Leagues");
                answer.add("LightYears");
                answer.add("Links");
                answer.add("Megameters");
                answer.add("Meters");
                answer.add("Microinches");
                answer.add("Micrometers");
                answer.add("Microns");
                answer.add("Miles");
                answer.add("Millimeters");
                answer.add("Nails");
                answer.add("Nanometers");
                answer.add("NauticalLeaguesInternational");
                answer.add("NauticalLeaguesUK");
                answer.add("NauticalMilesInternational");
                answer.add("NauticalMilesUK");
                answer.add("NauticalMilesUSCustomary");
                answer.add("Perches");
                answer.add("Petameters");
                answer.add("Picometers");
                answer.add("Poles");
                answer.add("Rods");
                answer.add("Ropes");
                answer.add("Spans");
                answer.add("Terameters");
                answer.add("ThousandthInches");
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

            // Light
            case 9:

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

            // Power
            case 11:

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

                answer.add("AcreFeetUSSurvey");
                answer.add("AcreInches");
                answer.add("ArceFeet");
                answer.add("Attoliters");
                answer.add("BarrelsOfOil");
                answer.add("BarrelsUK");
                answer.add("BarrelsUS");
                answer.add("BoardFeet");
                answer.add("Centiliters");
                answer.add("Cords");
                answer.add("CubicCentimeters");
                answer.add("CubicDecimeters");
                answer.add("CubicFeet");
                answer.add("CubicInches");
                answer.add("CubicKilometers");
                answer.add("CubicMeters");
                answer.add("CubicMiles");
                answer.add("CubicMillimeters");
                answer.add("CubicYards");
                answer.add("CupsMetric");
                answer.add("CupsUK");
                answer.add("CupsUS");
                answer.add("Deciliters");
                answer.add("Decisteres");
                answer.add("Dekaliters");
                answer.add("Dekasteres");
                answer.add("DessertspoonsUK");
                answer.add("DessertspoonsUS");
                answer.add("Drops");
                answer.add("Exaliters");
                answer.add("Femtoliters");
                answer.add("FluidOuncesUK");
                answer.add("FluidOuncesUS");
                answer.add("GallonsUK");
                answer.add("GallonsUS");
                answer.add("Gigaliters");
                answer.add("GillsUK");
                answer.add("GillsUS");
                answer.add("Hectoliters");
                answer.add("Hogsheads");
                answer.add("HundredCubicFeet");
                answer.add("Kiloliters");
                answer.add("Liters");
                answer.add("Megaliters");
                answer.add("Microliters");
                answer.add("Milliliters");
                answer.add("MinimsUK");
                answer.add("MinimsUS");
                answer.add("Nanoliters");
                answer.add("Petaliters");
                answer.add("Picoliters");
                answer.add("PintsUK");
                answer.add("PintsUS");
                answer.add("QuartsUK");
                answer.add("QuartsUS");
                answer.add("RegisterTons");
                answer.add("Steres");
                answer.add("TablespoonsMetric");
                answer.add("TablespoonsUK");
                answer.add("TablespoonsUS");
                answer.add("TeaspoonsMetric");
                answer.add("TeaspoonsUK");
                answer.add("TeaspoonsUS");
                answer.add("Teraliters");
                answer.add("Tuns");

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

            // Denisty
            case 4:

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

                break;

            // Force
            case 8:

                break;

            // Light
            case 9:

                break;

            // Mass
            case 10:

                break;

            // Power
            case 11:

                break;

            // Pressure
            case 12:

                break;

            // Speed
            case 13:

                break;

            // Time
            case 14:

                break;

            // Torque
            case 15:

                break;

            // Volume
            case 16:



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

            case "Area":
                unitOf=new UnitOf.Area().fromAres(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "Arpents":
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
            case "Hectares":
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
            case "SquareCentimeters":
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
            case "SquareFeet":
                unitOf=new UnitOf.Area().fromSquareFeet(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "SquareHectometers":
                unitOf=new UnitOf.Area().fromSquareHectometers(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "SquareInches":
                unitOf=new UnitOf.Area().fromSquareInches(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "SquareKilometers":
                unitOf=new UnitOf.Area().fromSquareKilometers(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "SquareMeters":
                unitOf=new UnitOf.Area().fromSquareMeters(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "SquareMicrometers":
                unitOf=new UnitOf.Area().fromSquareMicrometers(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "SquareMiles":
                unitOf=new UnitOf.Area().fromSquareMiles(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;
            case "SquareMillimeters":
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
            case "SquareYards":
                unitOf=new UnitOf.Area().fromSquareYards(Double.valueOf(value));
                ans=AreaHelper(answer_unit,unitOf);
                break;

        }
        return ans;
    }

    private String AreaHelper(String answer_unit,UnitOf.Area unitOf){
        String ans="0";
        
        switch (answer_unit){

            case"Ares":
                ans=String.valueOf(unitOf.toAres());
                break;
            case"Arpents":
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
            case"Hectares":
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
            case"SquareCentimeters":
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
            case"SquareFeet":
                ans=String.valueOf(unitOf.toSquareFeet());
                break;
            case"SquareHectometers":
                ans=String.valueOf(unitOf.toSquareHectometers());
                break;
            case"SquareInches":
                ans=String.valueOf(unitOf.toSquareInches());
                break;
            case"SquareKilometers":
                ans=String.valueOf(unitOf.toSquareKilometers());
                break;
            case"SquareMeters":
                ans=String.valueOf(unitOf.toSquareMeters());
                break;
            case"SquareMicrometers":
                ans=String.valueOf(unitOf.toSquareMicrometers());
                break;
            case"SquareMiles":
                ans=String.valueOf(unitOf.toSquareMiles());
                break;
            case"SquareMillimeters":
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
            case"SquareYards":
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
            case "Centimeters":
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
            case "Feet":
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
            case "Inches":
                unitOf=new UnitOf.Length().fromInches(Double.valueOf(value));
                ans=DistanceHelper(answer_unit,unitOf);
                break;
            case "Kilometers":
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
            case "Meters":
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
            case "Millimeters":
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
            case "Centimeters":
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
            case "Feet":
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
            case "Inches":
                ans=String.valueOf(unitOf.toInches());
                break;
            case "Kilometers":
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
            case "Meters":
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
            case "Millimeters":
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

}
