using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection.Emit;
using System.Text;
using System.Threading.Tasks;

namespace DGM_windows
{
    class scheduleInfo
    {
        public class IsHaveAPICheck
        {
            public int status { get; set; }
        }
        public class Root
        {
            public int status { get; set; }
            public string message { get; set; }
            public data data { get; set; }
        }
        public class data
        {
            public List<schedules> schedules { get; set; }
        }
        public class schedules
        {
            public string name { get; set; }
            public string date { get; set; }
        }
    }
}
