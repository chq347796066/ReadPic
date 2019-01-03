#!/bin/bash
total_time_str=$(ffmpeg -i $1 2>&1 | grep 'Duration' | cut -d ' ' -f 4 | sed s/,//)
OLD_IFS="$IFS"
IFS=":"
arr=($total_time_str)
IFS="$OLD_IFS"
hour=${arr[0]}
minute=${arr[1]}
second=${arr[2]}
second=${second%%.*}
hour_s=`expr $hour \* 3600`
minute_s=`expr $minute \* 60`
total_second=`expr $hour_s + $minute_s + $second`
echo $total_second
cut_time=$2
times=`expr $total_second / $cut_time`
echo $times
size=$3
fps=$4
input_video_name=$1
out_dir=${input_video_name%%.*}
rm -rf $out_dir
mkdir $out_dir
int=0
# ffmpeg  -i $video_name -strict -2  -s $size -r $fps $video_name -y
m="_m"
while (($int<$times))
do 
	start_time_second=`expr $cut_time \* $int`
	start_time_hour=`expr $start_time_second / 3600`
	start_time_hour_use=`expr $start_time_hour \* 3600`
	start_time_minute_left=`expr $start_time_second - $start_time_hour_use`
	start_time_minute=`expr $start_time_minute_left / 60`
	start_time_minute_use=`expr $start_time_minute \* 60`
	start_time_sec_1=`expr $start_time_second - $start_time_hour_use`
	start_time_sec=`expr $start_time_sec_1 - $start_time_minute_use`
	start_time_ss=$start_time_hour":"$start_time_minute":"$start_time_sec
	echo $start_time_ss
	video_m_name=$out_dir"/"$start_time_ss$m".mp4"
	video_name=$out_dir"/"$start_time_ss".mp4"
	echo $video_name
	ffmpeg -ss $start_time_ss  -i $input_video_name -vcodec copy -acodec copy -t  $cut_time  $video_m_name -y
	ffmpeg  -i $video_m_name -strict -2  -s $size -r $fps $video_name -y
	rm -rf $video_m_name
	let "int++"
	echo "======="
done
