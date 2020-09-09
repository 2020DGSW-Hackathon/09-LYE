using Newtonsoft.Json.Linq;
using System;
using System.IO;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;

namespace 해커톤
{
    class Program
    {
        static string targetURL = "http://kyungwon-server.kro.kr:8080/meals?school_id=7240393&office_code=D10&date=20200908";

        static void Main(string[] args)
        {

            //1.WebClient 클래스 활용
            string webClientResult = callWebClient();

            var r = JObject.Parse(webClientResult);

            var list = r["data"];

            //Console.WriteLine(r + Environment.NewLine + list);
            string replaceStr = (string)list["meals"][2];   //0은 아침, 1은 점심, 2는 저녁
            replaceStr = replaceStr.Replace("<br/>", Environment.NewLine); //br태그 삭제 후 줄바꿈
        }


        public static string callWebClient()
        {
            string result = string.Empty;
            try
            {
                WebClient client = new WebClient();

                //특정 요청 헤더값을 추가해준다. 
                client.Headers.Add("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; .NET CLR 1.0.3705;)");

                using (Stream data = client.OpenRead(targetURL))
                {
                    using (StreamReader reader = new StreamReader(data))
                    {
                        string s = reader.ReadToEnd();
                        result = s;

                        reader.Close();
                        data.Close();
                    }
                }

            }
            catch (Exception e)
            {
                //통신 실패시 처리로직
                Console.WriteLine(e.ToString());
            }
            return result;
        }


        public static string callWebRequest()
        {
            string responseFromServer = string.Empty;

            try
            {

                WebRequest request = WebRequest.Create(targetURL);
                request.Method = "GET";
                request.ContentType = "application/json";

                using (WebResponse response = request.GetResponse())
                using (Stream dataStream = response.GetResponseStream())
                using (StreamReader reader = new StreamReader(dataStream))
                {
                    responseFromServer = reader.ReadToEnd();
                }

            }
            catch (Exception e)
            {
                Console.WriteLine(e.ToString());
            }

            return responseFromServer;
        }
    }
}
