using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using System.Net;

namespace DGM_windows
{
    class GetSchoolWeather
    {
        public static string[] getWeather()
        {
            WebClient web = new WebClient();
            web.Encoding = Encoding.UTF8;
            string url = string.Format("http://api.openweathermap.org/data/2.5/weather?lat=35.663053&lon=128.413726&APPID=09c8dfc52b7541d33c528d09a55e2c18&units=metric");

            var json = web.DownloadString(url);

            var result = JsonConvert.DeserializeObject<WeatherInfo.Root>(json);

            WeatherInfo.Root outPut = result;

            string[] returnResult = new string[] {
                string.Format("{0} \u00B0" + "C", outPut.main.temp),
                GetSchoolWeatherContent.GetWeatherContent(string.Format("{0}",outPut.weather[0].icon)),
                GetSchoolWeatherContent.GetWeatherImage(string.Format("{0}",outPut.weather[0].icon))
                };

            return returnResult;
        }
    }
}
