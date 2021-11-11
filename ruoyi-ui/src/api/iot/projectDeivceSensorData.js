import request from '@/utils/request'

// 查询项目设备数据表列表
export function listProjectDeivceSensorData(query) {
  return request({
    url: '/iot/projectDeivceSensorData/list',
    method: 'post',
    data: query
  })
}
