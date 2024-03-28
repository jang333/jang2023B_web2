import { useState } from "react";
import TemperatureInput from "./TemperatureInput";

function BoilingVerdict(props){
    console.log(props)

    if (props.celsius>=100){
        return <p>물이 끓습니다.</p>
    } return <p>물이 끓지 않습니다.</p>

}

function toCelsius(fahrenheit){
    console.log(fahrenheit)
    return ((fahrenheit -32)*5)/9;
}

function toFahrenheit(celsius){
    console.log(celsius)
    return(celsius*9)/5+32;
}

function tryConvert(temperature, convert){
    const input = parseFloat(temperature);
    if(Number.isNaN(input)){
        return "";
    }

    const output = convert(input);
    console.log(output)
    const rounded = Math.round(output*1000)/1000;
    console.log(rounded)
    return rounded.toString();
}

export default function Calculator(props){
    const [temperature, setTemperture] = useState("");
    const [scale, setScale] = useState("c");

    const handleCelsiusChage = (temperature) => {
        setTemperture(temperature);
        setScale("c");
    };

    const handleFehrenheitChange = (temperature) => {
        setTemperture(temperature);
        setScale("f");    
    }

    const celsius = 
        scale === "f" ? tryConvert(temperature, toCelsius) : temperature;
    const fahrenheit = 
        scale === "c" ? tryConvert(temperature, toFahrenheit) : temperature;

    return(<>
        <div>
            <TemperatureInput
                scale = "c"
                temperature = {celsius}
                onTemperatureChange = {handleCelsiusChage}            
            />
            <TemperatureInput
                scale = "f"
                temperature = {fahrenheit}
                onTemperatureChange = {handleFehrenheitChange}
            />
            <BoilingVerdict celsius = {parseFloat(celsius)} />
                

        </div>
    
    </>)




}
