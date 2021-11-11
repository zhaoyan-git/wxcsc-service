import request from '@/utils/request'

// 查询测点数据表列表
export function listStructurePointData(query) {
  return request({
    url: '/iot/structurePointData/list',
    method: 'post',
    data: query
  })
}
