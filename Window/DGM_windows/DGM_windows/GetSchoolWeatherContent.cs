using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DGM_windows
{
    class GetSchoolWeatherContent
    {
        public static string GetWeatherContent(string icon)
        {
            if (icon == "01d")
            {
                return "아잇 눈부셔";
            }
            else if (icon == "01n")
            {
                return "소화도 할 겸 산책 어떤가요?";
            }
            else if (icon == "02d")
            {
                return "오늘 야자는 강당이다";
            }
            else if (icon == "02n")
            {
                return "창문 열고 자면 추울 것 같아요";
            }
            else if (icon == "03d" || icon == "03n" || icon == "04d" || icon == "04n")
            {
                return "어두컴컴하니깐 텐션 떨어져요";
            }
            else if (icon == "09d" || icon == "09n" || icon == "10n" || icon == "10d")
            {
                return "실내점호! 실내점호! 실내점호!";
            }
            else if (icon == "11d" || icon == "11n")
            {
                return "좀 더 격렬하게 실내점호! 실내점호!";
            }
            else if (icon == "13d" || icon == "13n")
            {
                return "눈이 와요! 우와아";
            }
            else if (icon == "50d" || icon == "50n")
            {
                return "조금 습하네요";
            }
            else
            {
                return "설정된 값이 없네요 ㅠㅠㅠ";
            }
        }
        public static string GetWeatherImage(string icon)
        {
            if (icon == "01d")
            {
                return "sun";
            }
            else if (icon == "01n")
            {
                return "night";
            }
            else if (icon == "02d")
            {
                return "sun_cloud";
            }
            else if (icon == "02n")
            {
                return "sun_cloud";
            }
            else if (icon == "03d" || icon == "03n" || icon == "04d" || icon == "04n")
            {
                return "cloud";
            }
            else if (icon == "09d" || icon == "09n" || icon == "10n" || icon == "10d")
            {
                return "rain";
            }
            else if (icon == "11d" || icon == "11n")
            {
                return "thunder";
            }
            else if (icon == "13d" || icon == "13n")
            {
                return "snow";
            }
            else if (icon == "50d" || icon == "50n")
            {
                return "mist";
            }
            else
            {
                return "exit";
            }
        }
    }
}
