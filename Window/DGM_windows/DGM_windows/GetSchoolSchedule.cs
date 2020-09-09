using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using System.Net;

namespace DGM_windows
{
    class GetSchoolSchedule
    {
        public static string[] getSchedule()
        {
            WebClient web = new WebClient();
            web.Encoding = Encoding.UTF8;
            string url = string.Format("http://kyungwon-server.kro.kr:8080/schedule?school_id=7240393&office_code=D10&date={0}", MainWindow.Today);

            try
            {
                var json = web.DownloadString(url);

                var result = JsonConvert.DeserializeObject<scheduleInfo.Root>(json);

                scheduleInfo.Root outPut = result;

                string[] returnResult = new string[] {
                    string.Format("{0}", outPut.data.schedules[0].name),
                };

                return returnResult;
            }
            catch
            {
                return new string[] { "오늘의 일정이 없습니다." };
            }
        }
    }
}
